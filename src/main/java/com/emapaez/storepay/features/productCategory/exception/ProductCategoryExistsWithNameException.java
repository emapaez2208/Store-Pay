package com.emapaez.storepay.features.productCategory.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class ProductCategoryExistsWithNameException extends EntityExistsWithAtributeException {
    public ProductCategoryExistsWithNameException(String message) {
        super(message);
    }
    public ProductCategoryExistsWithNameException(){super("The product category already exists with that name");}
}
