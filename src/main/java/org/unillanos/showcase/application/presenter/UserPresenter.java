package org.unillanos.showcase.application.presenter;

import java.util.List;

import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

public interface UserPresenter {
    UserResponseModel prepareSuccessView(UserResponseModel userResponseModel);
    UserResponseModel prepareFailView (String error);
    List<UserResponseModel> prepareSuccessView(List<UserResponseModel> users);
}
