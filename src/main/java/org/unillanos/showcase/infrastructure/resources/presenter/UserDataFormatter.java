package org.unillanos.showcase.infrastructure.resources.presenter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.unillanos.showcase.application.presenter.UserPresenter;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;
import org.unillanos.showcase.infrastructure.resources.rest.UserController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDataFormatter implements UserPresenter {

    @Override
    public UserResponseModel prepareSuccessView(UserResponseModel userResponseModel) {
        userResponseModel.setLink(WebMvcLinkBuilder.linkTo(UserController.class).slash(userResponseModel.getId()).withSelfRel());
        log.info("Saved user: " + userResponseModel);
        return userResponseModel;
    }

    @Override
    public UserResponseModel prepareFailView(String error) {
        log.error(error);
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }

    @Override
    public List<UserResponseModel> prepareSuccessView(List<UserResponseModel> users) {
        return users.stream()
        .map(user ->  {
            user.setLink(WebMvcLinkBuilder.linkTo(UserController.class).slash(user.getId()).withSelfRel());
            return user;
        })
        .collect(Collectors.toList());
    }

}