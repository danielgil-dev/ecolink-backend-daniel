package com.ecolink.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageNotValidExtension extends RuntimeException {
    public ImageNotValidExtension(String message) {
        super(message);
    }
}
