package com.emapaez.storepay.features.store.exception;

public class StoreExistsWithNameException extends RuntimeException {
    public StoreExistsWithNameException(String message) {
        super(message);
    }
    public StoreExistsWithNameException(){
        super("Store exists with this name");
    }
}
