package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.UUID;

public interface ICartItemService {
    CartItemResponse getByExternalId(UUID externalId);
    CartItemEntity create(CartItemRequest request);
    Page<CartItemResponse> getAll(int page,
                                  int size,
                                  Long quantityMin,
                                  Long quantityMax,
                                  BigDecimal priceMin,
                                  BigDecimal priceMax,
                                  String productName,
                                  String storeName,
                                  UUID cartId);
    CartItemResponse updatePrice(UUID externalId);
    CartItemResponse updateQuantity(UUID externalId, Long quantity);
    void delete(UUID externalId);
}
