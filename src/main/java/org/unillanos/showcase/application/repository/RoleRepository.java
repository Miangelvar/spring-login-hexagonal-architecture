package org.unillanos.showcase.application.repository;

import java.util.Set;

import org.unillanos.showcase.domain.model.Role;

public interface RoleRepository {
    Set<Role> findAll();
    Role save(Role role);
    boolean existsByName(String name);
}
