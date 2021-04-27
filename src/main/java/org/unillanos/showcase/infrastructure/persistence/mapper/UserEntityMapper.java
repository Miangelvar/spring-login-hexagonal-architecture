package org.unillanos.showcase.infrastructure.persistence.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.persistence.jpa.entity.UserEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserEntityMapper {

    @Autowired
    private final ModelMapper modelMapper;

    public UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    public User toDomain(UserEntity userEntity) {
        return modelMapper.map(userEntity, User.class);
    }
}
