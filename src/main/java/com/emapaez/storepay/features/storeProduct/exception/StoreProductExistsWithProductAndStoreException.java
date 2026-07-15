package com.emapaez.storepay.features.storeProduct.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class StoreProductExistsWithProductAndStoreException extends EntityExistsWithAtributeException{

    public StoreProductExistsWithProductAndStoreException(String message){super(message);}
    public StoreProductExistsWithProductAndStoreException(){super("The store product already exists with product and store.");}
}
