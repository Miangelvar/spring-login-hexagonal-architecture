package org.unillanos.showcase.application.service.service;

import org.unillanos.showcase.application.service.retrieve.GetUsersUseCase;
import org.unillanos.showcase.application.service.save.SaveUserUseCase;
import org.unillanos.showcase.application.service.search.FindUserUseCase;

public interface UserService extends SaveUserUseCase, GetUsersUseCase, FindUserUseCase {
    
}
