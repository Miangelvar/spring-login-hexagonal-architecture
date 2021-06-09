package org.unillanos.showcase.infrastructure.resources.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.unillanos.showcase.infrastructure.resources.dto.RegistrationForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegistrationForm> {

    @Override
    public boolean isValid(RegistrationForm formDto, ConstraintValidatorContext context) {
        return formDto.getPassword().equals(formDto.getPasswordConfirm());

    }

    

   

}
