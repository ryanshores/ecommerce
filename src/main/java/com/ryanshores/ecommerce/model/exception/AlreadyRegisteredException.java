package com.ryanshores.ecommerce.model.exception;

public class AlreadyRegisteredException extends Throwable {
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
