package org.unillanos.showcase.application.service.retrieve;

import java.util.Set;

import org.unillanos.showcase.domain.model.User;

public interface GetUsersUseCase {
    Set<User> findAll();
}
