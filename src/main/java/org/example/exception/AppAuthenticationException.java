package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AppAuthenticationException extends RuntimeException{
    public AppAuthenticationException() {
    }

    public AppAuthenticationException(String message) {
        super(message);
    }

    public AppAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppAuthenticationException(Throwable cause) {
        super(cause);
    }

    public AppAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
