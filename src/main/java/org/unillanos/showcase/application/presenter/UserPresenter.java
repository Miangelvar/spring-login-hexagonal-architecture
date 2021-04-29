package org.unillanos.showcase.application.presenter;

import org.unillanos.showcase.infrastructure.resources.dto.UserDto;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface UserPresenter {
    UserResponseModel prepareSuccessView (UserDto userDto);
    UserResponseModel prepareFailView (String error);
}
