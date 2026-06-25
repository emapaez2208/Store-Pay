package com.emapaez.storepay.features.store.exception;

public class StoreExistsWithCuitException extends RuntimeException {
    public StoreExistsWithCuitException(String message) {
        super(message);
    }
    public StoreExistsWithCuitException(){super("Store exists with this cuit");}
}
