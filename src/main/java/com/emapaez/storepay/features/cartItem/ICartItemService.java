package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;

import java.util.UUID;

public interface ICartItemService {
    CartItemResponse getByExternalId(UUID externalId);
    CartItemResponse create(CartItemRequest request);
}
