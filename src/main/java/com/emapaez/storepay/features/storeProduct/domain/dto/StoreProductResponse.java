package com.emapaez.storepay.features.storeProduct.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record StoreProductResponse(UUID externalId,
                                   String product,
                                   String description,
                                   BigDecimal suggestedPrice,
                                   String category,
                                   Long stock,
                                   BigDecimal price,
                                   Boolean enable
                                   ) {
}
