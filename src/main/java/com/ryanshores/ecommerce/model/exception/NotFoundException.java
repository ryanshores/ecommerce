package com.ryanshores.ecommerce.model.exception;

public class NotFoundException extends Throwable {
    public NotFoundException(String message) {
        super(message);
    }
}
