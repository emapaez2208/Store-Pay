package com.emapaez.storepay.features.store.domain.dto;

import java.util.UUID;

public record StoreResponse (UUID externalId,
                             String name,
                             String cuit,
                             String description){
}
