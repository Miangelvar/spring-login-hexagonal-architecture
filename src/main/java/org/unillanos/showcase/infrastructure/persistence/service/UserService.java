package org.unillanos.showcase.infrastructure.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.application.service.GetUsersUseCase;
import org.unillanos.showcase.application.service.SaveUserUseCase;
import org.unillanos.showcase.application.service.search.FindUserUseCase;
import org.unillanos.showcase.domain.model.User;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements SaveUserUseCase, GetUsersUseCase, FindUserUseCase{
    @Autowired
    private final UserRepository userRepository;
    private static final String COULD_NOT_SAVE_USER = "Could not save user";
    private static final String USER_NOT_FOUND = "User not found";
    @Override
    public Either<String, User> save(User user) {
        Either<String, User> result;
        if (!userRepository.existsByEmail(user.getUsername()) && !userRepository.existsByUsername(user.getUsername()))
            result = Either.right(userRepository.save(user)); 
        else {
            String msg = COULD_NOT_SAVE_USER + user + " email or username are already registered";
            result = Either.left(msg);
        }        
        return result;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Either<String, User> findById(Long id) {
        Either<String, User> result;
        var userOptional = userRepository.findById(id);
        result = userOptional.isPresent() ? Either.right(userOptional.get()) : Either.left(USER_NOT_FOUND + ":User id: " + id);        
        return result;
         
    }
}
