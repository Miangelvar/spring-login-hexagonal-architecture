package org.unillanos.showcase.application.service.retrieve;

import java.util.List;

import org.unillanos.showcase.domain.Role;

public interface GetRolesUseCase {
    List<Role> findAll();
}
