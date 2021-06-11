package org.unillanos.showcase.application.presenter;

import java.util.List;

import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface UserPresenter {
    UserResponseModel prepareSuccessView(User user);
    UserResponseModel prepareFailView (String error);
    List<UserResponseModel> prepareSuccessView(List<User> users);
}
