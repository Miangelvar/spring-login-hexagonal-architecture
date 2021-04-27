package org.unillanos.showcase.application.service.search;

import org.unillanos.showcase.domain.model.User;

import io.vavr.control.Either;

public interface FindUserUseCase {
    Either<String, User> findById(Long id);
}
