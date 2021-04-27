package org.unillanos.showcase.application.service;

import org.unillanos.showcase.domain.model.User;

public interface FindUserUseCase {
    User findById(Long id);
}
