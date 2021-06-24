package org.unillanos.showcase.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.domain.RoleRepository;
import org.unillanos.showcase.infrastructure.persistence.jpa.RoleEntity;
import org.unillanos.showcase.infrastructure.persistence.jpa.SpringDataRoleJpaRepository;
import org.unillanos.showcase.infrastructure.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SpringDataRoleRepository implements RoleRepository {

    @Autowired
    private final SpringDataRoleJpaRepository roleRepository;

    @Autowired
    private final ObjectMapperUtils mapper;

    @Override
    public List<Role> findAll() {
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

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name)
        .map(roleEntity -> Optional.of(mapper.map(roleEntity, Role.class)))
        .orElseGet(Optional::empty);
    }
    
}
