package com.emapaez.storepay.features.product.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class ProductExistsWithNameException extends EntityExistsWithAtributeException{

    public ProductExistsWithNameException(String message){super(message);}
    public ProductExistsWithNameException(){super("The product already exists with name.");}
}
