package org.unillanos.showcase.infrastructure.resources.mapper;

import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;
@Deprecated
public interface UserDtoMapper {
    User toDomain(UserDto userDto);
    UserDto toDto(User user);
}
