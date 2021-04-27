package org.unillanos.showcase.infrastructure.rest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.persistence.service.UserService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping(("/users"))
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
    
}
