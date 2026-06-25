package com.emapaez.storepay.features.user;

import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import com.emapaez.storepay.features.user.domain.dto.UserUpdate;

import java.util.UUID;

public interface IUserService {

    UserResponse create(UserRequest request);
    UserResponse findByExternalId(UUID externalId);
    void delete(UUID externalId);
    UserResponse update(UUID externalId, UserUpdate update);
}
