package org.unillanos.showcase.application.service.service;

import org.unillanos.showcase.application.service.delete.DeleteUserInteractor;
import org.unillanos.showcase.application.service.exists.ExistsUserInteractor;
import org.unillanos.showcase.application.service.retrieve.GetUsersInteractor;
import org.unillanos.showcase.application.service.save.SaveUserInteractor;
import org.unillanos.showcase.application.service.search.FindUserInteractor;

public interface UserInteractor extends DeleteUserInteractor, SaveUserInteractor, GetUsersInteractor, FindUserInteractor, ExistsUserInteractor  {
    
}
