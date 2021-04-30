package org.unillanos.showcase.application.service.save;

import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface SaveUserInteractor {
    UserResponseModel save(UserRegistrationForm userForm);
    
}
