package com.emapaez.storepay.features.store.exception;

import jakarta.persistence.EntityNotFoundException;

public class StoreNotFoundException extends EntityNotFoundException {
    public StoreNotFoundException(String message) {
        super(message);
    }
    public StoreNotFoundException(){super("Store not found");}
}
