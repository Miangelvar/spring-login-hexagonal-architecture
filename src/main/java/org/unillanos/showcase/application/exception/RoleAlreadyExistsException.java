package org.unillanos.showcase.application.exception;

public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(String msg) {
        super(msg);
    }
}
