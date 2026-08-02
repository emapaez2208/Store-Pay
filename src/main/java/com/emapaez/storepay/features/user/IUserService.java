package com.emapaez.storepay.features.user;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import com.emapaez.storepay.features.user.domain.dto.UserUpdate;

import java.util.UUID;

public interface IUserService {

    UserResponse create(UserRequest request);
    UserResponse getByExternalId(UUID externalId);
    void delete(UUID externalId);
    UserResponse update(UUID externalId, UserUpdate update);
    PageResponse<UserResponse> getAll(int page, int size, String name, String lastName, String dni, String email, Long phoneNumber, String store, Boolean enable);
    UserResponse createAdmin(UserRequest request);

}
