package org.unillanos.showcase.application.service.service;

import org.unillanos.showcase.application.service.retrieve.GetUsersInteractor;
import org.unillanos.showcase.application.service.save.SaveUserInteractor;
import org.unillanos.showcase.application.service.search.FindUserInteractor;

public interface UserInteractor extends SaveUserInteractor, GetUsersInteractor, FindUserInteractor  {
    
}
