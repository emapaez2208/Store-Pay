package com.emapaez.storepay.features.storeProduct.domain.dto;

import com.emapaez.storepay.features.product.domain.dto.ProductResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record StoreProductResponse(UUID externalId,
                                   ProductResponse product,
                                   Long stock,
                                   BigDecimal price,
                                   Instant createdAt,
                                   Instant updatedAt,
                                   Boolean enable
                                   ) {
}
