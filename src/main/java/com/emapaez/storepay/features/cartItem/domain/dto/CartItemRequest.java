package com.emapaez.storepay.features.cartItem.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record CartItemRequest(@Schema(description = "The quantity of the product to add to the cart", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
                              @NotNull(message = "Quantity is required")
                              @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
                              Long quantity,
                              @Schema(description = "The unique identifier of the store product", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
                              @NotNull(message = "Store product is required")
                              UUID storeProduct,
                              @Schema(description = "The unique identifier of the shopping cart", example = "550e8400-e29b-41d4-a716-446655440001", requiredMode = Schema.RequiredMode.REQUIRED)
                              @NotNull(message = "Cart is required")
                              UUID cart) {
}
