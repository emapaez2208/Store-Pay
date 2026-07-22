package com.emapaez.storepay.features.cart.exception;

import com.emapaez.storepay.common.exception.BusinessException;

public class InvalidPriceException extends BusinessException {
    public InvalidPriceException(String message) {
        super(message);
    }
}
