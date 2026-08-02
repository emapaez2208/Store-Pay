package com.emapaez.storepay.auth.credentials.exceptions;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class CredentialsNotFoundException extends EntityNotFoundCustomException {
    public CredentialsNotFoundException(String message) {
        super(message);
    }
}
