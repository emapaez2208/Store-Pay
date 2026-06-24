package com.emapaez.storepay.features.user.domain.dto;

import java.util.UUID;

public record UserResponse(UUID externalId,
                           String name,
                           String lastName,
                           String dni,
                           Long phoneNumber,
                           String store
                           ) {
}
