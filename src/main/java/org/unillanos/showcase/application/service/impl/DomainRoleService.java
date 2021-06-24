package org.unillanos.showcase.application.service.impl;

import java.util.List;

import org.unillanos.showcase.application.service.service.RoleService;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.domain.RoleRepository;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
 public class DomainRoleService implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Either<String, Role> save(Role role) {
        Either<String, Role> result = null;
        try {
            if (!roleRepository.existsByName(role.getName()))
                result = Either.right(roleRepository.save(role));
        } catch (Exception e) {
            result = Either.left("Could not be saved: " + role);
        }
        return result;
    }
    
}
