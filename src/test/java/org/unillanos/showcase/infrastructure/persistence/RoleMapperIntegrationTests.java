package org.unillanos.showcase.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.infrastructure.persistence.jpa.RoleEntity;
import org.unillanos.showcase.infrastructure.persistence.mapper.RoleEntityMapper;
import org.unillanos.showcase.infrastructure.persistence.mapper.RoleEntityMapperImpl;

@ExtendWith(SpringExtension.class)
public class RoleMapperIntegrationTests {
    @TestConfiguration
    static class EntityMapperIntegrationTestsConfig {
        @Bean
        public RoleEntityMapper userEntityMapper(ModelMapper mapper) {
            return new RoleEntityMapperImpl(mapper);
        }
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    private RoleEntityMapper roleMapper;

    @Test
    void whenConvertRoleEntityToRole_thenCorrect() {
        var roleEntity = RoleEntity.builder()
        .name("ADMIN")
        .description("System administrador")
        .build();

        var role = roleMapper.toDomain(roleEntity);

        assertEquals(role.getName(), roleEntity.getName());
        assertEquals(role.getDescription(), roleEntity.getDescription());
    }

    @Test
    void whenConvertRoleToRoleEntity_thenCorrect() {
        var role = Role.builder()
        .name("ADMIN")
        .description("System administrador")
        .build();

        var roleEntity = roleMapper.toEntity(role);

        assertEquals(role.getName(), roleEntity.getName());
        assertEquals(role.getDescription(), roleEntity.getDescription());
    }
}
