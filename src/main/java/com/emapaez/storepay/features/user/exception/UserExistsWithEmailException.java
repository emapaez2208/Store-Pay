package com.emapaez.storepay.features.user.exception;

public class UserExistsWithEmailException extends RuntimeException {
    public UserExistsWithEmailException(String message) {
        super(message);
    }
    public UserExistsWithEmailException(){super("User exists with this email.");}
}
