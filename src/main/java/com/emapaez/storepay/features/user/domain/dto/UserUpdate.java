package com.emapaez.storepay.features.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


public record UserUpdate(@Schema(description = "The user's name, max length: 50", example = "John", maxLength = 50, required = true)
                         @NotBlank(message = "The name cannot be null or blank")
                         @Size(max = 50, message = "The name must have a maximum length of 50 characters.")
                         String name,
                         @Schema(description = "The user's last name, max length: 50", example = "Doe", maxLength = 50, required = true)
                         @NotBlank(message = "The last name cannot be null or blank")
                         @Size(max = 50, message = "The last name must have a maximum length of 50 characters.")
                         String lastName,
                         @Schema(description = "The user's phone number", example = "2235456789", required = true)
                         @NotNull(message = "The phone number cannot be null")
                         @Positive(message = "The phone number must be a valid number.")
                         @Max(value = 99999999999L, message = "The phone number must have a maximum of 11 digits.")
                         Long phoneNumber) {
}
