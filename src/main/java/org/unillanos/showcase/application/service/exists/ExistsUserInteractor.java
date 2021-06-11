package org.unillanos.showcase.application.service.exists;

public interface ExistsUserInteractor {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
