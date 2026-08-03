package com.emapaez.storepay.auth.exception;

import com.emapaez.storepay.common.exception.GeneralTokenException;

public class RefreshTokenExpiredException extends GeneralTokenException {
    public RefreshTokenExpiredException(String message) {
        super(message);
    }
  public RefreshTokenExpiredException() {super("The refresh token has expired. Please log in again.");}
}
