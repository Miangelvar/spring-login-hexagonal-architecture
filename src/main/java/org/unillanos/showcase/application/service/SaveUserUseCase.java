package org.unillanos.showcase.application.service;

import org.unillanos.showcase.domain.model.User;

import io.vavr.control.Either;

public interface SaveUserUseCase {
     Either<String, User> save(User user);  
}
