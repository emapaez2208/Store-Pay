package com.emapaez.storepay.features.saleItem.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record SaleItemResponse(UUID externalId,
                               Long quantity,
                               BigDecimal price,
                               BigDecimal subTotal,
                               String product) {
}
