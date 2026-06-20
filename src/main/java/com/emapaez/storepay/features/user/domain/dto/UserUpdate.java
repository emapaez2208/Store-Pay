package com.emapaez.storepay.features.user.domain.dto;

import com.emapaez.storepay.common.model.Email;
import com.emapaez.storepay.common.validAnotation.ValidDni;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record UserUpdate(@NotBlank(message = "The name cannot be null or blank")
                         String name,
                         @NotBlank(message = "The last name cannot be null or blank.")
                         String lastName,
                         @Positive(message = "The phone number must be a valid number.")
                         @Max(value = 11, message = "The phone number must have a maximum of 11 digits.")
                         Long phoneNumber) {
}
