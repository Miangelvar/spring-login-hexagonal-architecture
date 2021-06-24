package org.unillanos.showcase.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoleJpaRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String name);
    Optional<RoleEntity> findByName(String name);
}
