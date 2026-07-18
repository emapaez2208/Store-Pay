package com.emapaez.storepay.features.cart;

import com.emapaez.storepay.features.cart.domain.dto.CartRequest;
import com.emapaez.storepay.features.cart.domain.dto.CartResponse;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;

import java.util.List;
import java.util.UUID;

public interface ICartService {

    CartResponse getByExternalId(UUID externalId);
    List<CartResponse> getByStore(UUID storeId);
    CartResponse create(CartRequest request);
    CartResponse agreeItem(CartItemRequest itemRequest);
}
