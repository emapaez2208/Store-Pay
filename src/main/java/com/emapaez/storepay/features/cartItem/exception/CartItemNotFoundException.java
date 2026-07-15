package com.emapaez.storepay.features.cartItem.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class CartItemNotFoundException extends EntityNotFoundCustomException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
    public CartItemNotFoundException(){super("Cart item not found");}
}
