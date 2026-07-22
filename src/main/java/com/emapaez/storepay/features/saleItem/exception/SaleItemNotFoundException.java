package com.emapaez.storepay.features.saleItem.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class SaleItemNotFoundException extends EntityNotFoundCustomException {
    public SaleItemNotFoundException(String message) {
        super(message);
    }
    public SaleItemNotFoundException(){super("Sale item not found.");}
}
