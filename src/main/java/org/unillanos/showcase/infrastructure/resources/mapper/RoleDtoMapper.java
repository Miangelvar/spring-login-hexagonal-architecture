package org.unillanos.showcase.infrastructure.resources.mapper;

import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.infrastructure.resources.dto.RoleDto;

public interface RoleDtoMapper {
    RoleDto toDto(Role role);
    Role toDomain(RoleDto roleDto);
}
