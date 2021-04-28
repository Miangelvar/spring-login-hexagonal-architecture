package org.unillanos.showcase.application.service.save;

import org.unillanos.showcase.domain.model.Role;

import io.vavr.control.Either;

public interface SaveRoleUseCase {
    Either<String, Role> save(Role role);
}
