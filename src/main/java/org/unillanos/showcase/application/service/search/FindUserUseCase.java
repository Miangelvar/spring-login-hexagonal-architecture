package org.unillanos.showcase.application.service.search;

import org.unillanos.showcase.domain.model.User;

public interface FindUserUseCase {
    User findById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);


}
