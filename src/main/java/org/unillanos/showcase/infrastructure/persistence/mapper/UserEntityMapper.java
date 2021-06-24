package org.unillanos.showcase.infrastructure.persistence.mapper;

import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.infrastructure.persistence.jpa.UserEntity;

public interface UserEntityMapper {
    User toDomain(UserEntity entity);
    UserEntity toEntity(User user);
}
