package com.emapaez.storepay.common.exception;

import jakarta.persistence.EntityNotFoundException;

public class EntityNotFoundCustomException extends EntityNotFoundException {
    public EntityNotFoundCustomException(String message) {
        super(message);
    }
}
