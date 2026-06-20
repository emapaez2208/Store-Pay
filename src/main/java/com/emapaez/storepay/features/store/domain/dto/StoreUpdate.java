package com.emapaez.storepay.features.store.domain.dto;

import com.emapaez.storepay.common.validAnotation.ValidCuit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StoreUpdate(@NotBlank(message = "The name cannot be null or blank.")
                          @Size(max = 30, message = "The name must be a maximum of 30 characters.")
                          String name,
                          @Size(max = 200, message = "The description must be a maximum of 30 characters.")
                          String description) {
}
