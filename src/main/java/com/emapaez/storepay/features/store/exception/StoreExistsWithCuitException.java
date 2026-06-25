package com.emapaez.storepay.features.store.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class StoreExistsWithCuitException extends EntityExistsWithAtributeException {
    public StoreExistsWithCuitException(String message) {
        super(message);
    }
    public StoreExistsWithCuitException(){super("Store exists with this cuit");}
}
