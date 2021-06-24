package org.unillanos.showcase.application.save;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.unillanos.showcase.application.exception.UserAlreadyExistsException;
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
public class UserCreatorInteractor implements UserCreator {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    public UserResponseModel save(UserRegistrationForm userForm) {
        if (userRepository.existsByEmail(userForm.getEmail())) {
            throw new UserAlreadyExistsException("There's already an user registered with that email address.");
        }
        if (userRepository.existsByUsername(userForm.getUsername())) {
            throw new UserAlreadyExistsException("There's already an user registered with that username.");
        }

        final var user = mapper.map(userForm, User.class);
        user.setActive(true);
        user.setCreatedDate(LocalDateTime.now());
        user.setRole(createRoleIfNotFound("USER", "Common user."));

        return mapper.map(userRepository.save(user), UserResponseModel.class);


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
}
