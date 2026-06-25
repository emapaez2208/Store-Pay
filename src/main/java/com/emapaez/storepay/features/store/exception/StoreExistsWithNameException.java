package com.emapaez.storepay.features.store.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class StoreExistsWithNameException extends EntityExistsWithAtributeException {
    public StoreExistsWithNameException(String message) {
        super(message);
    }
    public StoreExistsWithNameException(){
        super("Store exists with this name");
    }
}
