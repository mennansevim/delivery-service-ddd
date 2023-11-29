package com.global.delivery.application.exception;

public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException() {
        super();
    }

    public DeliveryNotFoundException(String message) {
        super(message);
    }

    public DeliveryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DeliveryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
