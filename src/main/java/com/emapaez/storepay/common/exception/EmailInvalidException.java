package com.emapaez.storepay.common.exception;

public class EmailInvalidException extends IllegalArgumentException {
    public EmailInvalidException(String message) {
        super(message);
    }
}
