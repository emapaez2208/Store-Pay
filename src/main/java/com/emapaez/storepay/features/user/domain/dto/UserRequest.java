package com.emapaez.storepay.features.user.domain.dto;

import com.emapaez.storepay.common.model.Email;
import com.emapaez.storepay.common.validAnotation.ValidDni;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.UUID;


public record UserRequest(@Schema(description = "The user's name, max length: 50.", example = "John", maxLength = 50, requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotBlank(message = "The name cannot be null or blank.")
                          @Size(max = 50, message = "The name must have a maximum length of 50 characters.")
                          String name,
                          @Schema(description = "The user's last name, max length: 50.", example = "Doe", maxLength = 50, requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotBlank(message = "The last name cannot be null or blank.")
                          @Size(max = 50, message = "The last name must have a maximum length of 50 characters.")
                          String lastName,
                          @Schema(description = "Argentinian DNI. Accepted formats: 12345678 or 12.345.678.", example = "12.456.789", requiredMode = Schema.RequiredMode.REQUIRED)
                          @ValidDni(message = "The DNI must be valid, with between 7 and 8 digits.")
                          String dni,
                          @Schema(description = "The user's Email.", example = "johndoe@email.com", requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "The email cannot be null.")
                          Email email,
                          @Schema(description = "The user's phone number.", example = "2235456789", requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "The phone number cannot be null.")
                          @Positive(message = "The phone number must be a valid number.")
                          @Max(value = 99999999999L, message = "The phone number must have a maximum of 11 digits.")
                          Long phoneNumber,
                          @Schema(description = "The externalId of Store where the user works.", example = "123e4567-e89b-12d3-a456-426614174000", requiredMode = Schema.RequiredMode.REQUIRED)
                          @NotNull(message = "The store cannot be null.")
                          UUID store) {
}
