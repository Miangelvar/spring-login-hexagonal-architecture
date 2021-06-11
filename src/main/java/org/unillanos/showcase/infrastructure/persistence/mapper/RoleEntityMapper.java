package org.unillanos.showcase.infrastructure.persistence.mapper;

import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.RoleEntity;

public interface RoleEntityMapper {
    Role toDomain(RoleEntity roleEntity);
    RoleEntity toEntity(Role role);
}
