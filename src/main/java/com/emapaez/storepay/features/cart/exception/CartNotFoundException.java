package com.emapaez.storepay.features.cart.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class CartNotFoundException extends EntityNotFoundCustomException {
    public CartNotFoundException(String message) {
        super(message);
    }
    public CartNotFoundException(){super("Cart not found");}
}
