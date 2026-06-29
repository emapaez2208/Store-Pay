package com.emapaez.storepay.features.cart.domain.dto;

import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponse(UUID externalId,
                           StoreResponse store,
                           BigDecimal totalPrice,
                           Integer discount,
                           List<CartItemResponse> items) {
}
