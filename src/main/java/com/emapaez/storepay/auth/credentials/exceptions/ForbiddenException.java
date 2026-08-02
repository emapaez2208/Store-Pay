package com.emapaez.storepay.auth.credentials.exceptions;

import com.emapaez.storepay.common.exception.BusinessException;

public class ForbiddenException extends BusinessException {
    public ForbiddenException(String message) {
        super(message);
    }
}
