package com.emapaez.storepay.features.cartItem.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class CartItemExistsWithStoreProductAndCartException extends EntityExistsWithAtributeException {
    public CartItemExistsWithStoreProductAndCartException(String message) {
        super(message);
    }
    public CartItemExistsWithStoreProductAndCartException(){super("Cart item already exists with store product and cart.");}
}
