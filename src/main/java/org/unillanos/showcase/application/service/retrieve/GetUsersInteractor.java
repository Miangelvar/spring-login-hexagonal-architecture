package org.unillanos.showcase.application.service.retrieve;

import java.util.List;

import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface GetUsersInteractor {
    List<UserResponseModel> getUsers();
}
