package org.unillanos.showcase.application.service.save;

import org.unillanos.showcase.domain.User;
import org.unillanos.showcase.infrastructure.resources.dto.RegistrationForm;
import org.unillanos.showcase.infrastructure.resources.dto.UserDto;

public interface SaveUserUseCase {
     User save(UserDto userDto);  
     User save(RegistrationForm userForm);

}
