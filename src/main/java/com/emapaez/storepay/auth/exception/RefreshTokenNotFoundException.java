package com.emapaez.storepay.auth.exception;

import com.emapaez.storepay.common.exception.GeneralTokenException;

public class RefreshTokenNotFoundException extends GeneralTokenException {
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }
    public RefreshTokenNotFoundException(){super("No refresh token is associated with this account. Please log in again.");}
}
