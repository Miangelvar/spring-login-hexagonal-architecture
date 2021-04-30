package org.unillanos.showcase.infrastructure.resources.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.unillanos.showcase.infrastructure.resources.validation.PasswordMatches;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserRegistrationForm implements RegistrationForm, Serializable {
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
