package com.emapaez.storepay.features.store.domain.dto;

import com.emapaez.storepay.common.validAnotation.ValidCuit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StoreUpdate(@Schema(description = "The store name, max length: 30", example = "Tech Store", maxLength = 30, required = true)
                          @NotBlank(message = "The name cannot be null or blank.")
                          @Size(max = 30, message = "The name must be a maximum of 30 characters.")
                          String name,
                          @Schema(description = "Store description, max length: 200", example = "Retail store specialized in technology products.", maxLength = 200, required = true)
                          @NotBlank(message = "The description cannot be null or blank.")
                          @Size(max = 200, message = "The description must be a maximum of 200 characters.")
                          String description) {
}
