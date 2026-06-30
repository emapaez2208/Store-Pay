package com.emapaez.storepay.features.store.domain.dto;

import com.emapaez.storepay.common.validAnotation.ValidCuit;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StoreRequest(

        @Schema(description = "The store name, max length: 30", example = "Tech Store", maxLength = 30, requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "The name cannot be null or blank.")
        @Size(max = 30, message = "The name must be a maximum of 30 characters.")
        String name,
        @Schema(description = "Argentinian CUIT/CUIL. Accepted formats: 20384971777 or 20-38497177-7", example = "20-38497177-7", maxLength = 13, requiredMode = Schema.RequiredMode.REQUIRED)
        @ValidCuit(message = "The CUIT/CUIL must be valid.")
        @Size(max = 13, message = "The CUIT/CUIL must have a maximum length of 13 characters.")
        String cuit,
        @Schema(description = "Store description, max length: 200", example = "Retail store specialized in technology products.", maxLength = 200, requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "The description cannot be null or blank.")
        @Size(max = 200, message = "The description must be a maximum of 200 characters.")
        String description
        ) {
}
