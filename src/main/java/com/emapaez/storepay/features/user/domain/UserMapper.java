package com.emapaez.storepay.features.user.domain;

import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserRequest request);

    @Mapping(target = "store", source = "store.name")
    UserResponse toDto(UserEntity entity);
}
