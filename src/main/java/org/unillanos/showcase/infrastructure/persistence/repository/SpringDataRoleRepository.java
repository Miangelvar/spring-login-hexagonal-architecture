package org.unillanos.showcase.infrastructure.persistence.repository;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.unillanos.showcase.application.repository.RoleRepository;
import org.unillanos.showcase.domain.model.Role;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.RoleEntity;
import org.unillanos.showcase.infrastructure.persistence.jpa.repository.SpringDataRoleEntityJpaRepository;
import org.unillanos.showcase.infrastructure.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SpringDataRoleRepository implements RoleRepository {

    @Autowired
    private final SpringDataRoleEntityJpaRepository roleRepository;

    @Autowired
    private final ObjectMapperUtils mapper;

    @Override
    public Set<Role> findAll() {
        return mapper.mapAll(roleRepository.findAll(), Role.class);
    }

    @Override
    public Role save(Role role) {
        return mapper.map(roleRepository.save(mapper.map(role, RoleEntity.class)), Role.class);
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }
    
}
