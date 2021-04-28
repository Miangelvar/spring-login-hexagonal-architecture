package org.unillanos.showcase.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unillanos.showcase.application.repository.RoleRepository;
import org.unillanos.showcase.application.repository.UserRepository;
import org.unillanos.showcase.application.service.implementation.DomainRoleService;
import org.unillanos.showcase.application.service.implementation.DomainUserService;
import org.unillanos.showcase.application.service.service.RoleService;
import org.unillanos.showcase.application.service.service.UserService;

@Configuration
public class UserServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public UserService userService(UserRepository userRepository) {
        return new DomainUserService(userRepository);
    }
    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new DomainRoleService(roleRepository);
    }
}

