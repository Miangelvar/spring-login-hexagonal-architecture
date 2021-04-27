package org.unillanos.showcase.infrastructure.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.application.service.FindUserUseCase;
import org.unillanos.showcase.application.service.GetUsersUseCase;
import org.unillanos.showcase.application.service.SaveUserUseCase;
import org.unillanos.showcase.domain.exception.UserNotFoundException;
import org.unillanos.showcase.domain.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements SaveUserUseCase, GetUsersUseCase, FindUserUseCase{
    @Autowired
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (!userRepository.existsByEmail(user.getUsername()) && !userRepository.existsByUsername(user.getUsername()))
            return userRepository.save(user);
        else
            return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
        {   
            String msg = "User with id " + id + "was not found";
            log.error(msg, new UserNotFoundException(msg));
            return new UserNotFoundException(msg);
        });
    }
    
}
