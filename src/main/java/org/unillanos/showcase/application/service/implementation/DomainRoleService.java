package org.unillanos.showcase.application.service.implementation;

import java.util.Set;

import org.unillanos.showcase.application.repository.RoleRepository;
import org.unillanos.showcase.application.service.service.RoleService;
import org.unillanos.showcase.domain.model.Role;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
 public class DomainRoleService implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<Role> findAll() {
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
