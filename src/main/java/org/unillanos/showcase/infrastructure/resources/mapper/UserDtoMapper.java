package org.unillanos.showcase.infrastructure.resources.mapper;

import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;

public interface UserDtoMapper {
    User toDomain(UserDto userDto);
    UserDto toDto(User user);
}
