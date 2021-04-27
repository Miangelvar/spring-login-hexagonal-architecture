package org.unillanos.showcase.infrastructure.rest.restcontroller.mapper;

import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.rest.dto.UserDto;

public interface UserDtoMapper {
    User toDomain(UserDto userDto);
    UserDto toDto(User user);
}
