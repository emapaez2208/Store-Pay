package com.emapaez.storepay.features.storeProduct.exception;

import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;

public class StoreProductNotFoundException extends EntityNotFoundCustomException{

    public StoreProductNotFoundException(String message){super(message);}
    public StoreProductNotFoundException(){super("Store product not found");}

}
