package org.unillanos.showcase.application.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.unillanos.showcase.application.exception.UserAlreadyExistsException;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.repository.RoleRepository;
import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.application.service.service.UserService;
import org.unillanos.showcase.domain.model.Role;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.resources.dto.RegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Deprecated
@Slf4j
@RequiredArgsConstructor
public class DomainUserService implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    
    private static final String USER_ALREADY_EXISTS = "There is an account with that email address or username.";
    private static final String USER_NOT_FOUND = "User not found.";
    
    @Override
    public User save(UserDto userDto) {
        if(!isValid(userDto.getEmail(), userDto.getUsername())) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        }
        return userRepository.save(mapper.map(userDto, User.class));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + " User id: " + id));
    }
    

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
        .orElseThrow( () -> new  UserNotFoundException(USER_NOT_FOUND + " email: " + email));
    }

    @Override
    public User save(RegistrationForm userForm) throws UserAlreadyExistsException {
        if(!isValid(userForm.getEmail(), userForm.getUsername())) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        }
        User user = mapper.map(userForm, User.class);
        user.setRole(createRoleIfNotFound("USER", "Common user."));
        return userRepository.save(mapper.map(userForm, User.class));
    }

    private boolean isValid(String email, String username) {
        return !userRepository.existsByEmail(email) && !userRepository.existsByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND + " username: " + username));
    }

    private Role createRoleIfNotFound(String name, String description) {
        return roleRepository.findByName(name)
        .orElseGet(() -> {
            log.info("Role " + name + "not found: Creating new role with name " + name + " and description " + description);
            return roleRepository.save(
                Role.builder()
                .name(name)
                .description(description)
                .build());
        });
    }
}
