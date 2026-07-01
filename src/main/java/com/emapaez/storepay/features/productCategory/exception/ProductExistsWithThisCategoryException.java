package com.emapaez.storepay.features.productCategory.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class ProductExistsWithThisCategoryException extends EntityExistsWithAtributeException {
    public ProductExistsWithThisCategoryException(String message) {
        super(message);
    }
}
