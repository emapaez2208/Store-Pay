package com.emapaez.storepay.features.product.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class ProductNotFoundException extends EntityNotFoundCustomException{

    public ProductNotFoundException(String message){super(message);}
    public ProductNotFoundException(){super("Product not found");}
    
}
