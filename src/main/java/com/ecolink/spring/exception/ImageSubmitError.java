package com.ecolink.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageSubmitError extends RuntimeException {
    public ImageSubmitError(String message){
        super(message);
    }
}
