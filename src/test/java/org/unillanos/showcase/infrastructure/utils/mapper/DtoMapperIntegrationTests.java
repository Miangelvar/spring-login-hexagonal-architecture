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
import org.unillanos.showcase.infrastructure.resources.dto.RoleDto;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;
import org.unillanos.showcase.infrastructure.resources.mapper.UserDtoMapper;
import org.unillanos.showcase.infrastructure.resources.mapper.UserDtoMapperImpl;

@ExtendWith(SpringExtension.class)
public class DtoMapperIntegrationTests {
    @TestConfiguration
    static class DtoMapperIntegrationTestsConfig {
        @Bean
        UserDtoMapper userDtoMapper(ModelMapper mapper) {
            return new UserDtoMapperImpl(mapper);
        }
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    private UserDtoMapper userMapper;

    @Test
    public void whenConvertUserDomainToUserDto_thenCorrect() {
        var user = User.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .role(Role.builder().id(1L).name("ADMIN").description("System administrador").build())
        .build();

        var userDto = userMapper.toDto(user);

        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getRole().getName(), userDto.getRole().getName());
        assertEquals(user.getRole().getDescription(), userDto.getRole().getDescription());
        assertEquals(user.getRole().getId(), userDto.getRole().getId());
    }

    @Test
    public void whenConvertUserDtoToUserDomain_thenCorrect() {
        var userDto = UserDto.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .role(RoleDto.builder().name("ADMIN").description("System administrador").build())
        .build();

        var user = userMapper.toDomain(userDto);

        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getRole().getName(), userDto.getRole().getName());
        assertEquals(user.getRole().getDescription(), userDto.getRole().getDescription());
    }
}
