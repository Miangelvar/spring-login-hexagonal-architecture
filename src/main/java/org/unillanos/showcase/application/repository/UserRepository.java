package org.unillanos.showcase.application.repository;

import java.util.Optional;
import java.util.Set;

import org.unillanos.showcase.domain.model.User;

public interface UserRepository {
    Set<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User save(User user);
}
