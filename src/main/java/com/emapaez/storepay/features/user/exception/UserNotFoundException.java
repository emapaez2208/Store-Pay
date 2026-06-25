package com.emapaez.storepay.features.user.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class UserNotFoundException extends EntityNotFoundCustomException {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(){super("User not found.");}
}
