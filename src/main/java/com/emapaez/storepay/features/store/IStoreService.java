package com.emapaez.storepay.features.store;

import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreUpdate;

import java.util.UUID;

public interface IStoreService {

    StoreResponse create(StoreRequest request);
    public StoreResponse findByExternalId(UUID externalId);
    void delete(UUID externalId);
    StoreResponse update(UUID externalId, StoreUpdate update);

}
