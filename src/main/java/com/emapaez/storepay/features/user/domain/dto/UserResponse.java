package com.emapaez.storepay.features.user.domain.dto;

import com.emapaez.storepay.common.model.Email;

import java.util.UUID;

public record UserResponse(UUID externalId,
                           String name,
                           String lastName,
                           Long phoneNumber,
                           String store
                           ) {
}
