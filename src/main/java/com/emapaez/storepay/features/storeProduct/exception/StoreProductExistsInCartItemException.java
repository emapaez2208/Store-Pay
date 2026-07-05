package com.emapaez.storepay.features.storeProduct.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class StoreProductExistsInCartItemException extends EntityExistsWithAtributeException{

    public StoreProductExistsInCartItemException(String message){super(message);}
}
