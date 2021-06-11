package org.unillanos.showcase.infrastructure.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

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

import io.vavr.collection.Stream;

@ExtendWith(SpringExtension.class)
public class ModelMapperIntegrationTests {
    @TestConfiguration
    static class TestsConfig {
        @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }
        @Bean
        public Faker faker() {
            return new Faker();
        }
    }

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Faker faker;

    @Test
    public void whenConvertUserToUserDto_thenCorrect() {
        var user = User.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .role(Role.builder().id(1L).name("ADMIN").description("System administrador").build())
        .build();

        UserDto userDto = mapper.map(user, UserDto.class);

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

        User user = mapper.map(userDto, User.class);

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
        .name(faker.name().firstName())
        .description(faker.name().lastName())
        .build();

        var roleEntity = mapper.map(role, RoleEntity.class);

        assertEquals(role.getName(), roleEntity.getName());
        assertEquals(role.getDescription(), roleEntity.getDescription());
    }

    @Test
    void whenConvertRoleDtoListToRoleList_thenCorrect() {
        Set<RoleDto> rolesDtos = new HashSet<>();
        Stream.range(0, 5)
        .forEach(i -> rolesDtos.add(
            RoleDto.builder()
            .id(faker.random().nextLong())
            .name(faker.name().firstName())
            .description(faker.name().lastName())
            .build()
            ));

        var roles = rolesDtos.stream()
        .map(roleDto -> mapper.map(roleDto, Role.class))
        .collect(Collectors.toSet());

        assertEquals(rolesDtos.size(), roles.size());
    }
}
