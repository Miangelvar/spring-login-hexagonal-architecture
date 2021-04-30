package org.unillanos.showcase.application.repository;

import java.util.List;
import java.util.Optional;

import org.unillanos.showcase.domain.model.User;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
