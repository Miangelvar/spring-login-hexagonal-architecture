package org.unillanos.showcase.application.service.retrieve;

import java.util.List;

import org.unillanos.showcase.domain.User;

public interface GetUsersUseCase {
    List<User> findAll();
}
