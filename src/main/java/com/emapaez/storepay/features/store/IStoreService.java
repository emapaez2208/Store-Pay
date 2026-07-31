package com.emapaez.storepay.features.store;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreUpdate;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface IStoreService {

    StoreResponse create(StoreRequest request);
    StoreResponse getByExternalId(UUID externalId);
    void delete(UUID externalId);
    StoreResponse update(UUID externalId, StoreUpdate update);
    PageResponse<StoreResponse> getAll(int page,
                                       int size,
                                       String name,
                                       String cuit,
                                       Boolean enable);
    List<UserResponse> agreeUser(UUID storeId, UUID userId);
    List<UserResponse> removeUser(UUID storeId, UUID userId);
    List<UserResponse> getUsers(UUID storeId);
}
