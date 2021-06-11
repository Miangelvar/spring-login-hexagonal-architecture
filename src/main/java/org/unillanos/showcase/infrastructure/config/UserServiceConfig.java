package org.unillanos.showcase.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unillanos.showcase.application.presenter.UserPresenter;
import org.unillanos.showcase.application.service.implementation.DomainRoleService;
import org.unillanos.showcase.application.service.implementation.DomainUserInteractor;
import org.unillanos.showcase.application.service.service.RoleService;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.domain.RoleRepository;
import org.unillanos.showcase.domain.UserRepository;

@Configuration
public class UserServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new DomainRoleService(roleRepository);
    }
    @Bean
    public UserInteractor userInteractor(UserRepository userRepository, RoleRepository roleRepository, UserPresenter userPresenter, ModelMapper mapper) {
        return new DomainUserInteractor(userRepository, roleRepository, userPresenter, mapper);
    }
}

