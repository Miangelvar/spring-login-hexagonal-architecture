package org.unillanos.showcase.application.save;

import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface UserCreator {
    UserResponseModel save(UserRegistrationForm userForm);
}
