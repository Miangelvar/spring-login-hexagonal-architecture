package org.unillanos.showcase.infrastructure.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.rest.dto.UserDto;
import org.unillanos.showcase.infrastructure.rest.restcontroller.mapper.UserDtoMapper;
import org.unillanos.showcase.infrastructure.rest.restcontroller.mapper.UserDtoMapperImpl;

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
        .build();

        var userDto = userMapper.toDto(user);

        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void whenConvertUserDtoToUserDomain_thenCorrect() {
        var userDto = UserDto.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .build();

        var user = userMapper.toDomain(userDto);

        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getEmail(), userDto.getEmail());
    }
}
