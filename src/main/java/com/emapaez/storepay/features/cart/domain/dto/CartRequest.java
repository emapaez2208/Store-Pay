package com.emapaez.storepay.features.cart.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CartRequest(@Schema(description = "The unique identifier of the store", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "Store is required")
                          UUID store,
                          @Schema(description = "The discount percentage. Must be between 0 and 100", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "Discount is required")
                          @Min(value = 0, message = "Discount must be at least 0")
                          @Max(value = 100, message = "Discount must not exceed 100")
                          Integer discount) {
}
