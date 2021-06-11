package org.unillanos.showcase.domain;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository {
    Optional<Role> findByName(String name);
    Set<Role> findAll();
    Role save(Role role);
    boolean existsByName(String name);
}
