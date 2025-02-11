package com.ecolink.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LikeAlredyExistsException extends RuntimeException {
    public LikeAlredyExistsException(String message) {
        super(message);
    }
}
