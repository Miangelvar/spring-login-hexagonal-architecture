package org.unillanos.showcase.infrastructure.resources.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

import lombok.RequiredArgsConstructor;

@Deprecated
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResponseController {
    private final UserInteractor service;

    @PostMapping
    public ResponseEntity<UserResponseModel> saveUser(@RequestBody @Valid UserRegistrationForm userForm, BindingResult result) {                
        if (result.hasErrors()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE ,
                result.toString()
                );
        }
        UserResponseModel response = service.save(userForm);
        return ResponseEntity.created(response.getLink().toUri()).body(response);      
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseModel>> getUsers() {
        return ResponseEntity.ok(
            service.getUsers()
        );
    }
}
