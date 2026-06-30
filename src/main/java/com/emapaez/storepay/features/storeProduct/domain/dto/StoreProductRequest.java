package com.emapaez.storepay.features.storeProduct.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record StoreProductRequest(@Schema(description = "The unique identifier of the product", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
                                  @NotNull(message = "Product is required")
                                  UUID product,
                                  @Schema(description = "The unique identifier of the store", example = "550e8400-e29b-41d4-a716-446655440001", requiredMode = Schema.RequiredMode.REQUIRED)
                                  @NotNull(message = "Store is required")
                                  UUID store,
                                  @Schema(description = "The product price. It can contain a maximum of 8 digits before the decimal point and 2 digits after it", example = "110.99", requiredMode = Schema.RequiredMode.REQUIRED)
                                  @NotNull(message = "Price is required")
                                  @PositiveOrZero(message = "Price must be greater than or equal to zero")
                                  @Digits(integer = 8, fraction = 2, message = "Price can contain a maximum of 8 digits before the decimal point and 2 digits after it")
                                  BigDecimal price,
                                  @Schema(description = "The available stock quantity for the product in the store", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
                                  @NotNull(message = "Stock is required")
                                  @PositiveOrZero(message = "Stock must be greater than or equal to zero")
                                  Long stock) {
}
