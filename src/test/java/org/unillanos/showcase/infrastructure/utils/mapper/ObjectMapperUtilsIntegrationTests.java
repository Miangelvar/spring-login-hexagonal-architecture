package org.unillanos.showcase.infrastructure.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.RoleEntity;
import org.unillanos.showcase.infrastructure.resources.dto.RoleDto;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;

@ExtendWith(SpringExtension.class)
public class ObjectMapperUtilsIntegrationTests {
    @TestConfiguration
    static class TestsConfig {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
        @Bean
        public ObjectMapperUtils objectMapperUtils (ModelMapper mapper) {
            return new ObjectMapperUtilsImpl(mapper);
        }
    }

    @Autowired
    private ObjectMapperUtils mapper;

    @Test
    public void whenConvertUserToUserDto_thenCorrect() {
        var user = User.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .role(Role.builder().id(1L).name("ADMIN").description("System administrador").build())
        .build();

        var userDto = mapper.map(user, UserDto.class);

        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getRole().getName(), userDto.getRole().getName());
        assertEquals(user.getRole().getDescription(), userDto.getRole().getDescription());
        assertEquals(user.getRole().getId(), userDto.getRole().getId());
    }

    @Test
    public void whenConvertUserDtoToUser_thenCorrect() {
        var userDto = UserDto.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .role(RoleDto.builder().id(1L).name("ADMIN").description("System administrador").build())
        .build();

        var user = mapper.map(userDto, User.class);

        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getRole().getName(), userDto.getRole().getName());
        assertEquals(user.getRole().getDescription(), userDto.getRole().getDescription());
        assertEquals(user.getRole().getId(), userDto.getRole().getId());
    }

    @Test
    void whenConvertRoleEntityToRole_thenCorrect() {
        var roleEntity = RoleEntity.builder()
        .name("ADMIN")
        .description("System administrador")
        .build();

        var role = mapper.map(roleEntity, Role.class);

        assertEquals(role.getName(), roleEntity.getName());
        assertEquals(role.getDescription(), roleEntity.getDescription());
    }

    @Test
    void whenConvertRoleToRoleEntity_thenCorrect() {
        var role = Role.builder()
        .name("ADMIN")
        .description("System administrador")
        .build();

        var roleEntity = mapper.map(role, RoleEntity.class);

        assertEquals(role.getName(), roleEntity.getName());
        assertEquals(role.getDescription(), roleEntity.getDescription());
    }}
