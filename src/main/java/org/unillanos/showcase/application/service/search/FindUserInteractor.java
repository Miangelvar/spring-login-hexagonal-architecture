package org.unillanos.showcase.application.service.search;

import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface FindUserInteractor {
    UserResponseModel findById(Long id);
}
