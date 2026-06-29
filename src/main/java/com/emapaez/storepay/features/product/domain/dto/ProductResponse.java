package com.emapaez.storepay.features.product.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID externalId,
                              String name,
                              String description,
                              BigDecimal suggestedPrice,
                              String category) {
}
