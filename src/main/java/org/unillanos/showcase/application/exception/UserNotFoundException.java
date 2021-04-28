package org.unillanos.showcase.application.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
    public UserNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
