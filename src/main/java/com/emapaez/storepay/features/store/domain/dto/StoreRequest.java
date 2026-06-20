package com.emapaez.storepay.features.store.domain.dto;

import com.emapaez.storepay.common.validAnotation.ValidCuit;
import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StoreRequest (@NotBlank(message = "The name cannot be null or blank.")
                            @Size(max = 30, message = "The name must be a maximum of 30 characters.")
                            String name,
                            @ValidCuit(message = "The CUIT/CUIL must be valid.")
                            String cuit,
                            @NotBlank(message = "The description cannot be null or blank.")
                            @Size(max = 200, message = "The description must be a maximum of 30 characters.")
                            String description
                            ){
}
