package org.unillanos.showcase.infrastructure.resources.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unillanos.showcase.application.exception.UserAlreadyExistsException;
import org.unillanos.showcase.application.service.service.UserInteractor;
import org.unillanos.showcase.infrastructure.resources.dto.UserRegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserResponseModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {
    @Autowired
    private final UserInteractor userService;
    private static final String REGISTRATION_VIEW = "signup/signup";
    private static final String REGISTER_SUCCESS = "User successfully registered.";

    @GetMapping(value = "/signup")
    public String showRegistrationForm(Model model) {
        var userForm = new UserRegistrationForm();
        model.addAttribute("userForm", userForm);
        return REGISTRATION_VIEW;
    }

    @PostMapping(value = "/signup")
    public String registerUserAccount(@ModelAttribute("userForm") @Valid UserRegistrationForm userForm, BindingResult result, Model model) {
        if (userService.existsByEmail(userForm.getEmail())) {
            result.addError(new FieldError("userForm", "email", "This email address is already registered."));
        }
        if(userService.existsByUsername(userForm.getUsername())) {
            result.addError(new FieldError("userForm", "username", "This username is already registered."));
        }
                    
        if (result.hasErrors()) {
            result.getAllErrors()
            .stream().
            forEach(error -> log.error(error.getDefaultMessage()));
            return REGISTRATION_VIEW;
        }
        

        else {
            try {
                UserResponseModel registeredUser = userService.save(userForm);
                log.info(REGISTER_SUCCESS + " " + registeredUser);
            } catch (UserAlreadyExistsException e) {
                log.error(e.getMessage());
                
            }
            return "redirect:/api/users";
        }        
    }

}
