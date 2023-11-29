package com.global.delivery.application.exception;

import org.springframework.http.HttpStatus;

public class GenericRestClientException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public GenericRestClientException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public GenericRestClientException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}