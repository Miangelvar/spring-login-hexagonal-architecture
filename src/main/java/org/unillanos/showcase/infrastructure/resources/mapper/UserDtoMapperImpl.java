package org.unillanos.showcase.infrastructure.resources.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDtoMapperImpl implements UserDtoMapper {
    @Autowired
    private final ModelMapper mapper;

    @Override
    public User toDomain(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    @Override
    public UserDto toDto(User user) {
        return mapper.map(user, UserDto.class);
    }
}
