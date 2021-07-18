package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AppAuthorizationException extends RuntimeException{
    public AppAuthorizationException() {
    }

    public AppAuthorizationException(String message) {
        super(message);
    }

    public AppAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppAuthorizationException(Throwable cause) {
        super(cause);
    }

    public AppAuthorizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
