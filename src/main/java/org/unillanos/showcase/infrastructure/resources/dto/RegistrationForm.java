package org.unillanos.showcase.infrastructure.resources.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.unillanos.showcase.infrastructure.resources.controller.validation.PasswordMatches;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordMatches
public class RegistrationForm implements Registration {
    @NotBlank
    private String username;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;
}
