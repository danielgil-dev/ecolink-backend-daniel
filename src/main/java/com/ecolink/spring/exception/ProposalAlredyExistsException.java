package com.ecolink.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProposalAlredyExistsException extends RuntimeException {
    public ProposalAlredyExistsException(String message) {
        super(message);
    }
}
