package com.ecolink.spring.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissionNotFoundException extends RuntimeException {
    public MissionNotFoundException(String message){
        super(message);
    }
}
