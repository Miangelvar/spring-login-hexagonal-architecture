package org.unillanos.showcase.infrastructure.resources.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.unillanos.showcase.infrastructure.resources.dto.Registration;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Registration form = (Registration) value;
        return form.getPassword().equals(form.getPasswordConfirm());
    }

   

}
