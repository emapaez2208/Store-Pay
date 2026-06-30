package com.emapaez.storepay.features.product.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(@Schema(description = "The product name, max length: 30", example = "Coca-Cola", maxLength = 30, requiredMode = Schema.RequiredMode.REQUIRED)
                             @NotBlank(message = "The name cannot be null or blank.")
                             @Size(max = 30, message = "The name must be a maximum of 30 characters.")
                             String name,
                             @Schema(description = "Product description, max length: 200", example = "the best product for drinking and staying hydrated.", maxLength = 200, requiredMode = Schema.RequiredMode.REQUIRED)
                             @NotBlank(message = "The description cannot be null or blank.")
                             @Size(max = 200, message = "The description must be a maximum of 200 characters.")
                             String description,
                             @Schema(description = "The suggested price to product, price can contain a maximum of 8 digits before the decimal point and 2 digits after it", example = "110.99", requiredMode = Schema.RequiredMode.REQUIRED)
                             @NotNull(message = "suggested price is required")
                             @PositiveOrZero(message = "suggested price must be a positive value")
                             @Digits(integer = 8, fraction = 2, message = "suggested price can contain a maximum of 8 digits before the decimal point and 2 digits after it")
                             BigDecimal suggestedPrice,
                             @Schema(description = "The externalId of product category.", example = "123e4567-e89b-12d3-a456-426614174000", requiredMode = Schema.RequiredMode.REQUIRED)
                             @NotNull(message = "The product category cannot be null.")
                             String productCategory) {
}
