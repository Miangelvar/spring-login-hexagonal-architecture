package org.unillanos.showcase.infrastructure.persistence.mock;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.infrastructure.persistence.repository.SpringDataRoleRepository;
import org.unillanos.showcase.infrastructure.persistence.repository.SpringDataUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SetupDatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    private final SpringDataUserRepository userRepository;
    private final SpringDataRoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        createUserIfNotFound("admin", "admin@example.com", "admin",
                createRoleIfNotFound("ADMIN", "System administrator"));

        createRoleIfNotFound("TECHNICIAN", "Installs or removes electricity meters"); 
        createRoleIfNotFound("WALKER", "Walker");
        createRoleIfNotFound("AUXILIARY", "Extra help for technicians");

        alreadySetup = true;
    }

    @Transactional
    public Role createRoleIfNotFound(String name, String description) {
        return roleRepository.findByName(name).orElseGet(() -> {
            log.info("Role not found: Creating new role with name " + name);
            return roleRepository.save(Role.builder().name(name).description(description).build());
        });
    }

    @Transactional
    public User createUserIfNotFound(String username, String email, String password, Role role) {
        return userRepository.findByUsername(username).orElseGet(() -> {
            log.info("User not found: Creating new user with username " + username);
            return userRepository
                    .save(User.builder().username(username).email(email).password(password).role(role).build());
        });
    }
}
