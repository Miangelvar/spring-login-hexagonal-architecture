package org.unillanos.showcase.infrastructure.resources.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.unillanos.showcase.application.exception.UserAlreadyExistsException;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.service.implementation.DomainUserService;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping(("api/users"))
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final DomainUserService userService;

    @Autowired
    private final ModelMapper mapper;
    private static final String REGISTER_SUCCESS = "User successfully registered.";
    private static final String USER_FOUND = "User was found.";

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto, BindingResult result) {
        ResponseEntity<UserDto> response = null;
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                // TODO: Handle object error password not matching.
                String errorField = error.getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(errorField, errorMessage);
            });
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, errors.toString()
            );
        }
        try {
            UserDto savedUser = mapper.map(userService.save(userDto), UserDto.class);
            log.info(REGISTER_SUCCESS + " " + savedUser);
            response = ResponseEntity.created(URI.create("api/users/" + savedUser.getId())).body(savedUser);
        } catch (UserAlreadyExistsException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return response;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        ResponseEntity<UserDto> response = null;
        try {
            var user = userService.findById(id);
            log.info(USER_FOUND + ": " + user);
            response = ResponseEntity.ok(mapper.map(user, UserDto.class));
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return response;
    }
    
    
    @GetMapping
    public ResponseEntity<Set<UserDto>> findAll() {
        return ResponseEntity.ok(
            userService.findAll()
            .stream()
            .map(user -> mapper.map(user, UserDto.class))
            .collect(Collectors.toSet())
        );
    }
    
}
