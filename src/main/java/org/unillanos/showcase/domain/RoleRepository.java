package org.unillanos.showcase.domain;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
    List<Role> findAll();
    Role save(Role role);
    boolean existsByName(String name);
}
