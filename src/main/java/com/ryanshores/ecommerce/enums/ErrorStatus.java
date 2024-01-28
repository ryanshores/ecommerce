package com.ryanshores.ecommerce.enums;

public enum ErrorStatus {
    UNKNOWN_ERROR("unknown error"),
    ALREADY_REGISTERED("email already registered"),
    BAD_REQUEST("bad request"),
    NOT_FOUND("resource not found"),
    ACCESS_DENIED("access denied");

    public final String label;

    ErrorStatus(String label) {
        this.label = label;
    }
}
