package org.unillanos.showcase.application.service.retrieve;

import java.util.Set;

import org.unillanos.showcase.domain.model.Role;

public interface GetRolesUseCase {
    Set<Role> findAll();
}
