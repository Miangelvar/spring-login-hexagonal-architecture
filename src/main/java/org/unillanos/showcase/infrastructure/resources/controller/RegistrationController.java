package org.unillanos.showcase.infrastructure.resources.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unillanos.showcase.application.exception.UserAlreadyExistsException;
import org.unillanos.showcase.application.service.service.UserService;
import org.unillanos.showcase.domain.model.User;
import org.unillanos.showcase.infrastructure.resources.dto.RegistrationForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
    private final UserService userService;
    private static final String REGISTRATION_VIEW = "registration";
    private static final String REGISTER_SUCCESS = "User successfully registered.";

    @GetMapping(value = "/register")
    public String showRegistrationForm(Model model) {
        var userForm = new RegistrationForm();
        model.addAttribute("user", userForm);
        return REGISTRATION_VIEW;
    }

    @PostMapping(value = "/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid RegistrationForm userForm, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
                User registeredUser = userService.save(userForm);
                log.info(REGISTER_SUCCESS + " " + registeredUser);
            } catch (UserAlreadyExistsException e) {
                log.error(e.getMessage());
            }
            return "redirect:/api/users";
            
        }
        else {
            result.getAllErrors().stream().forEach(error -> log.error(error.toString()));
        }
        return REGISTRATION_VIEW;
    }

}
