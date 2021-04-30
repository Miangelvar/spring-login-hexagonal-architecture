package org.unillanos.showcase.infrastructure.resources.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.unillanos.showcase.infrastructure.resources.validation.PasswordMatches;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Deprecated
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDto implements Serializable, RegistrationForm {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;
    private RoleDto role;
}
