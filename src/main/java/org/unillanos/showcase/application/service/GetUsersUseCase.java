package org.unillanos.showcase.application.service;

import java.util.List;

import org.unillanos.showcase.domain.model.User;

public interface GetUsersUseCase {
    List<User> getUsers();
}
