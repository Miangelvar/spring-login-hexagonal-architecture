package org.unillanos.showcase.infrastructure.resources.rest;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unillanos.showcase.application.service.implementation.DomainUserService;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;
import org.unillanos.showcase.infrastructure.resources.mapper.UserDtoMapper;

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
    private final UserDtoMapper userMapper;
    
    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (!result.hasErrors())
            return userService.save(userMapper.toDomain(userDto))
            .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user)))
            
            .getOrElseGet(msg -> {
                log.warn(msg);
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            });
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.findById(id)
        .map(user -> ResponseEntity.ok(userMapper.toDto(user)))
        .getOrElseGet(msg -> {
         log.warn(msg);
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  
        });
    }
    
    
    @GetMapping
    public ResponseEntity<Set<UserDto>> findAll() {
        var users = userService.findAll();
        return ResponseEntity.ok(
            users.stream()
            .map(userMapper::toDto)
            .collect(Collectors.toSet())
            );
    }
    
}
