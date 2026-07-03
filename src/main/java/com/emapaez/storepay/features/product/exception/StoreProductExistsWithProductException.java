package com.emapaez.storepay.features.product.exception;

import com.emapaez.storepay.common.exception.EntityExistsWithAtributeException;

public class StoreProductExistsWithProductException extends EntityExistsWithAtributeException{

    public StoreProductExistsWithProductException(String message){super(message);}

}
