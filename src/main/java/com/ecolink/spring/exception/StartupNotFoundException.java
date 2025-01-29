package com.ecolink.spring.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StartupNotFoundException extends RuntimeException {
    public StartupNotFoundException(String message) {
        super(message);
    }
}
