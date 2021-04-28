package org.unillanos.showcase.application.service.implementation;

import java.util.Set;

import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.application.service.service.UserService;
import org.unillanos.showcase.domain.model.User;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DomainUserService implements UserService {
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
    public Set<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Either<String, User> findById(Long id) {
        Either<String, User> result;
        var userOptional = userRepository.findById(id);
        result = userOptional.isPresent() ? Either.right(userOptional.get()) : Either.left(USER_NOT_FOUND + ":User id: " + id);        
        return result;
         
    }

    @Override
    public Either<String, User> findByUsername(String username) {
        Either<String, User> result;
        var userOptional = userRepository.findByUsername(username);
        result = userOptional.isPresent() ? Either.right(userOptional.get()) : Either.left(USER_NOT_FOUND + ":Username: " + username);        
        return result;
    }

    @Override
    public Either<String, User> findByEmail(String email) {
        Either<String, User> result;
        var userOptional = userRepository.findByEmail(email);
        result = userOptional.isPresent() ? Either.right(userOptional.get()) : Either.left(USER_NOT_FOUND + ":User email: " + email);      
        return result;
    }
}
