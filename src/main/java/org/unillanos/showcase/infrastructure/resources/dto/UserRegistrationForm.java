package org.unillanos.showcase.infrastructure.resources.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.unillanos.showcase.infrastructure.resources.validation.FieldMatch;

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
@FieldMatch(message = "Passwords don't match")
public class UserRegistrationForm implements RegistrationForm, Serializable {
    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Password confirmation is mandatory")
    private String passwordConfirm;
}
