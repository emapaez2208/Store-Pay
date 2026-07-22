package com.emapaez.storepay.features.sale.domain.dto;

import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SaleResponse(UUID externalId,
                           UUID storeId,
                           String storeName,
                           BigDecimal subTotal,
                           Integer discount,
                           BigDecimal totalPrice,
                           Instant createdAt) {
}
