package org.unillanos.showcase.infrastructure.resources.rest;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

import lombok.RequiredArgsConstructor;
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
        ResponseEntity<UserResponseModel> response = null;
        UserResponseModel userResponse;
        try {
            userResponse = service.findById(id);
            response.status(HttpStatus.FOUND).body(userResponse);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseModel>> getUsers() {
        return ResponseEntity.ok().body(service.getUsers());
    }

}
