package com.ecolink.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommentNotValidException extends RuntimeException {
    public CommentNotValidException(String message) {
        super(message);
    }
}
