package com.emapaez.storepay.features.store;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreUpdate;

import java.util.UUID;

public interface IStoreService {

    StoreResponse create(StoreRequest request);
    StoreResponse findByExternalId(UUID externalId);
    void delete(UUID externalId);
    StoreResponse update(UUID externalId, StoreUpdate update);
    PageResponse<StoreResponse> getAll(int page,
                                       int size,
                                       String name,
                                       String cuit,
                                       Boolean enable);

}
