package org.unillanos.showcase.application.service.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.unillanos.showcase.application.exception.UserNotFoundException;
import org.unillanos.showcase.application.presenter.UserPresenter;
import org.unillanos.showcase.application.repository.RoleRepository;
import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.domain.model.Role;
import org.unillanos.showcase.domain.model.User;
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
            return userPresenter.prepareFailView("Theres already an user account with that username or email address.");
        }
        User newUser = mapper.map(userForm, User.class);
        newUser.setCreatedDate(LocalDateTime.now());
        newUser.setRole(createRoleIfNotFound("USER", "Common user."));        
        return userPresenter.prepareSuccessView(userRepository.save(newUser));
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
        boolean isValid = false;
        try {
            isValid = !userRepository.existsByEmail(email) && !userRepository.existsByUsername(username);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return isValid;
    }

    @Override
    public List<UserResponseModel> getUsers() {
        final var users = userRepository.findAll();
        log.info("Interactor: " + users.toString());
        return userPresenter.prepareSuccessView(users);
    }

    @Override
    public UserResponseModel findById(Long id) {

        User user = userRepository.findById(id)
        .orElseThrow( () -> new UserNotFoundException("User with id " + id + " not found."));
        return userPresenter.prepareSuccessView(user);                  
    }

    
    
}
