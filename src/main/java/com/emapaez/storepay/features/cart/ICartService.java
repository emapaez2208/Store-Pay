package com.emapaez.storepay.features.cart;

import com.emapaez.storepay.features.cart.domain.dto.CartRequest;
import com.emapaez.storepay.features.cart.domain.dto.CartResponse;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ICartService {

    CartResponse getByExternalId(UUID externalId);
    List<CartResponse> getByStore(UUID storeId);
    CartResponse create(CartRequest request);
    CartResponse agreeItem(UUID cartId, CartItemRequest itemRequest);
    CartResponse removeItem(UUID cartId, UUID itemId);
    CartResponse updateItemQuantity(UUID cartId, UUID itemId, Long quantity);
    CartResponse updateItemPrice(UUID cartId, UUID itemId, BigDecimal newPrice);
    SaleResponse payCart(UUID cartId);

}
