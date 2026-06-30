package com.emapaez.storepay.features.cartItem.domain.dto;

import com.emapaez.storepay.features.product.domain.dto.ProductResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(UUID externalId,
                               Long quantity,
                               BigDecimal price,
                               String product) {
}
