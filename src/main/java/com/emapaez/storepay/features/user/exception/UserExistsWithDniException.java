package com.emapaez.storepay.features.user.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class UserExistsWithDniException extends EntityExistsWithAtributeException {
    public UserExistsWithDniException(String message) {
        super(message);
    }
    public UserExistsWithDniException(){super("User exists with this DNI.");}
}
