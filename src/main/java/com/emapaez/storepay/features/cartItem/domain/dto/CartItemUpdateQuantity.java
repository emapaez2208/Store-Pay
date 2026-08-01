package com.emapaez.storepay.features.cartItem.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CartItemUpdateQuantity(@Schema(description = "The quantity of the product to add to the cart", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
                                     @NotNull(message = "Quantity is required")
                                     @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
                                     Long quantity) {
}
