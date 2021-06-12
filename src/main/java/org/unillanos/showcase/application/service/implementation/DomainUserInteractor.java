package org.unillanos.showcase.application.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.presenter.UserPresenter;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.domain.RoleRepository;
import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.domain.UserRepository;
import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DomainUserInteractor implements UserInteractor {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserPresenter userPresenter;
    private final ModelMapper mapper;

    @Override
    public UserResponseModel save(UserRegistrationForm userForm) {
        if (!isValid(userForm.getEmail(), userForm.getUsername())) {
            return userPresenter.prepareFailView("There's already an user account with that username or email address.");
        }
        var newUser = mapper.map(userForm, User.class);
        newUser.setActive(true);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setRole(createRoleIfNotFound("USER", "Common user."));
        var user = userRepository.save(newUser);        
        return userPresenter.prepareSuccessView(mapper.map(user, UserResponseModel.class));
    }

    private Role createRoleIfNotFound(String name, String description) {
        return roleRepository.findByName(name)
        .orElseGet(() -> {
            log.info("Role not found: Creating new role with name " + name + " and description " + description);
            return roleRepository.save(
                Role.builder()
                .name(name)
                .description(description)
                .build());
        });
    }

    private boolean isValid(String email, String username) {
        return !userRepository.existsByEmail(email) && !userRepository.existsByUsername(username);
    }

    @Override
    public List<UserResponseModel> getUsers() {
        var users = userRepository.findAll()
        .stream()
        .map(user -> mapper.map(user, UserResponseModel.class))
        .collect(Collectors.toList());
        return userPresenter.prepareSuccessView(users);
    }

    @Override
    public UserResponseModel findById(Long id) {
        User user = userRepository.findById(id)
        .orElseThrow( () -> new UserNotFoundException("User with id " + id + " not found."));
        return userPresenter.prepareSuccessView(mapper.map(user, UserResponseModel.class));                  
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
