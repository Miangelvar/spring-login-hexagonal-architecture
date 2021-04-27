package org.unillanos.showcase.infrastructure.rest.restcontroller;

import java.util.List;

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
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.persistence.service.UserService;
import org.unillanos.showcase.infrastructure.rest.dto.UserDto;
import org.unillanos.showcase.infrastructure.rest.restcontroller.mapper.UserDtoMapper;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@RestController
@RequestMapping(("/users"))
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserDtoMapper userMapper;
    
    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto, BindingResult result) {
        return userService.save(userMapper.toDomain(userDto))
        .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user)))
        
        .getOrElseGet(msg -> {
            log.warn(msg);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        });
        

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
    public ResponseEntity<List<User>> findAll() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    
}
