package org.unillanos.showcase.application.repository;

import java.util.Optional;
import java.util.Set;

import org.unillanos.showcase.domain.model.Role;

public interface RoleRepository {
    Optional<Role> findByName(String name);
    Set<Role> findAll();
    Role save(Role role);
    boolean existsByName(String name);
}
