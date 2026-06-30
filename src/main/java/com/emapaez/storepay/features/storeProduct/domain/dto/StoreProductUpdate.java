package com.emapaez.storepay.features.storeProduct.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record StoreProductUpdate(@Schema(description = "The product price. It can contain a maximum of 8 digits before the decimal point and 2 digits after it", example = "110.99", requiredMode = Schema.RequiredMode.REQUIRED)
                                 @NotNull(message = "Price is required")
                                 @PositiveOrZero(message = "Price must be greater than or equal to zero")
                                 @Digits(integer = 8, fraction = 2, message = "Price can contain a maximum of 8 digits before the decimal point and 2 digits after it")
                                 BigDecimal price,
                                 @Schema(description = "The available stock quantity for the product in the store", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
                                 @NotNull(message = "Stock is required")
                                 @PositiveOrZero(message = "Stock must be greater than or equal to zero")
                                 Long stock) {
}
