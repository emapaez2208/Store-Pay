package com.emapaez.storepay.features.productCategory.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class ProductCategoryNotFoundException extends EntityNotFoundCustomException {
    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
    public ProductCategoryNotFoundException(){super("The product category not found.");}
}
