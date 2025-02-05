package com.ecolink.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageGetExcepcion extends RuntimeException {
    public ImageGetExcepcion(String message){
        super(message);
    }
}

