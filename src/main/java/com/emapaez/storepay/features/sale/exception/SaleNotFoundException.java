package com.emapaez.storepay.features.sale.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class SaleNotFoundException extends EntityNotFoundCustomException {
    public SaleNotFoundException(String message) {
        super(message);
    }
    public SaleNotFoundException(){super("Sale not found");}
}
