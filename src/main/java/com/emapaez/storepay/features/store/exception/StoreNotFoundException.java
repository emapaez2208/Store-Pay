package com.emapaez.storepay.features.store.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class StoreNotFoundException extends EntityNotFoundCustomException {
    public StoreNotFoundException(String message) {
        super(message);
    }
    public StoreNotFoundException(){super("Store not found");}
}
