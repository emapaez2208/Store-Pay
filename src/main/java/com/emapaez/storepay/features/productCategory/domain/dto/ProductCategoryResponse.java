package com.emapaez.storepay.features.productCategory.domain.dto;

import java.util.UUID;

public record ProductCategoryResponse(UUID externalId,
                                      String name,
                                      String description) {
}
