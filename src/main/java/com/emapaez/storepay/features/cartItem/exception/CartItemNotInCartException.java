package com.emapaez.storepay.features.cartItem.exception;

import com.emapaez.storepay.common.exception.BusinessException;

public class CartItemNotInCartException extends BusinessException {
    public CartItemNotInCartException(String message) {
        super(message);
    }
    public CartItemNotInCartException(){super("Cart item does not belong to cart.");}
}
