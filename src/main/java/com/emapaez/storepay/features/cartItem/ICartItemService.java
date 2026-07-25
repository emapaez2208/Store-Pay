package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ICartItemService {
    CartItemEntity getByExternalId(UUID externalId);
    CartItemEntity create(CartItemRequest request);
    PageResponse<CartItemResponse> getAll(int page,
                                          int size,
                                          Long quantityMin,
                                          Long quantityMax,
                                          BigDecimal priceMin,
                                          BigDecimal priceMax,
                                          String productName,
                                          String storeName,
                                          UUID cartId);
    void updatePrice(UUID externalId, BigDecimal newPrice);
    void updateQuantity(UUID externalId, Long quantity);
    void delete(UUID externalId);
    void deleteByCart(CartEntity cart);
}
