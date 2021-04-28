package org.unillanos.showcase.infrastructure.persistence.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.unillanos.showcase.domain.model.Role;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.RoleEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleEntityMapperImpl implements RoleEntityMapper {

    @Autowired
    private final ModelMapper mapper;
    
    @Override
    public Role toDomain(RoleEntity roleEntity) {
        return mapper.map(roleEntity, Role.class);
    }

    @Override
    public RoleEntity toEntity(Role role) {
        return mapper.map(role, RoleEntity.class);
    }

    
    
}
