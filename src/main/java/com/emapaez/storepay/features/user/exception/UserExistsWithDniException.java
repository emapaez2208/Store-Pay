package com.emapaez.storepay.features.user.exception;

public class UserExistsWithDniException extends RuntimeException {
    public UserExistsWithDniException(String message) {
        super(message);
    }
    public UserExistsWithDniException(){super("User exists with this DNI.");}
}
