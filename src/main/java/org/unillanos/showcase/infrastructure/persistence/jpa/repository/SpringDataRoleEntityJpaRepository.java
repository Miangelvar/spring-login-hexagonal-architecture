package org.unillanos.showcase.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.RoleEntity;

public interface SpringDataRoleEntityJpaRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String name);
}
