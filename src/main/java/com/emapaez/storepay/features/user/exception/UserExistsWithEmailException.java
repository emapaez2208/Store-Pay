package com.emapaez.storepay.features.user.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class UserExistsWithEmailException extends EntityExistsWithAtributeException {
    public UserExistsWithEmailException(String message) {
        super(message);
    }
    public UserExistsWithEmailException(){super("User exists with this email.");}
}
