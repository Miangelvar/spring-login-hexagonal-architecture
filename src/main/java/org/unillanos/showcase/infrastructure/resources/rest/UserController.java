package org.unillanos.showcase.infrastructure.resources.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(("api/users"))
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserInteractor service;

    @Autowired
    private final ModelMapper mapper;
    private static final String REGISTER_SUCCESS = "User successfully registered.";
    private static final String USER_FOUND = "User was found.";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName;
            if (error instanceof FieldError)
                fieldName = ((FieldError) error).getField();
            else {
                fieldName = error.getObjectName();
            }
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping
    public ResponseEntity<UserResponseModel> saveUser(@RequestBody @Valid UserRegistrationForm userForm) {
        UserResponseModel response = null;
        ResponseEntity<UserResponseModel> responseEntity = null;
        // TODO: Validation for user existence not working
        response = service.save(userForm);
        responseEntity = ResponseEntity.created(response.getLink().toUri()).body(response);

        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseModel> findById(@PathVariable Long id) {
        UserResponseModel userResponse;
        try {
            userResponse = service.findById(id);
            log.info(USER_FOUND + " " + userResponse.toString());
            return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseModel>> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseModel> replaceUser(@PathVariable Long id,
            @RequestBody UserRegistrationForm userForm) {
        try {
            var userResponse = service.findById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(userResponse);
        } catch (UserNotFoundException uEx) {
            log.error(uEx.getMessage(), uEx);
            return ResponseEntity.notFound().build();
        }
    }
}
