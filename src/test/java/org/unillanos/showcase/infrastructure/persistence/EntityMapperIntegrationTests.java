package org.unillanos.showcase.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.UserEntity;
import org.unillanos.showcase.infrastructure.persistence.mapper.UserEntityMapper;
import org.unillanos.showcase.infrastructure.persistence.mapper.UserEntityMapperImpl;


@ExtendWith(SpringExtension.class)
public class EntityMapperIntegrationTests {
    
    @TestConfiguration
    static class EntityMapperIntegrationTestsConfig {
        @Bean
        public UserEntityMapper userEntityMapper(ModelMapper mapper) {
            return new UserEntityMapperImpl(mapper);
        }
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    private UserEntityMapper mapper;

    @Test
    public void whenConvertUserDomainToUserEntity_thenCorrect() {
        var user = User.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .build();

        var userEntity = mapper.toEntity(
         user   
        );
        assertEquals(user.getId(),userEntity.getId());
        assertEquals(user.getUsername(),userEntity.getUsername());
        assertEquals(user.getEmail(), userEntity.getEmail());
    }

    @Test
    public void whenConvertUserEntityToUserDomain_thenCorrect() {
        var userEntity = UserEntity.builder()
        .id(1L)
        .username("admin")
        .email("admin@example.com")
        .password("admin")
        .build();

        var user = mapper.toDomain(userEntity);

        assertEquals(userEntity.getId(),user.getId());
        assertEquals(userEntity.getUsername(),user.getUsername());
        assertEquals(userEntity.getEmail(), user.getEmail());
    }
}
