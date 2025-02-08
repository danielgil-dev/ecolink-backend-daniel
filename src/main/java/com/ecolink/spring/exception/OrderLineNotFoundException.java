package com.ecolink.spring.exception;

public class OrderLineNotFoundException extends RuntimeException {

    public OrderLineNotFoundException(String message) {
        super(message);
    }

}