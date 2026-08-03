package com.emapaez.storepay.auth.exception;

import com.emapaez.storepay.common.exception.GeneralTokenException;

public class InvalidRefreshTokenException extends GeneralTokenException {
    public InvalidRefreshTokenException(String message){super(message);}
    public InvalidRefreshTokenException(){super("The refresh token is invalid");}
}
