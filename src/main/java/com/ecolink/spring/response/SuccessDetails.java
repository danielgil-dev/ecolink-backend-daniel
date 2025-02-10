package com.ecolink.spring.response;

import org.springframework.http.HttpStatus;

public class SuccessDetails {
    private int status;
    private String message;

    public SuccessDetails(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public SuccessDetails(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}