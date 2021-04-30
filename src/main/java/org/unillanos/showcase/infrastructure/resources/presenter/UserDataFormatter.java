package org.unillanos.showcase.infrastructure.resources.presenter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.unillanos.showcase.application.presenter.UserPresenter;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;
import org.unillanos.showcase.infrastructure.resources.rest.UserController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataFormatter implements UserPresenter {
    @Autowired
    private final ModelMapper mapper;

    @Override
    public UserResponseModel prepareSuccessView(final User user) {
        UserResponseModel response = mapper.map(user, UserResponseModel.class);
        response.setLink(WebMvcLinkBuilder.linkTo(UserController.class).slash(response.getId()).withSelfRel());
        log.info("Saved user: " + response);
        return response;
    }

    @Override
    public UserResponseModel prepareFailView(String error) {
        log.error(error);
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }

    @Override
    public List<UserResponseModel> prepareSuccessView(List<User> users) {
        List<UserResponseModel> responses = new ArrayList<>();
        UserResponseModel userResponse;
        log.info("Presenter: " + users.toString());
        for (User user : users) {
            userResponse = mapper.map(user, UserResponseModel.class);
            userResponse.setLink(WebMvcLinkBuilder.linkTo(UserController.class).slash(userResponse.getId()).withSelfRel());
            log.info("Mapped user to Response: " + userResponse.toString());
            responses.add(userResponse);
        }    
        log.info("Response list size: " + responses.size());
        return responses;
    }

}