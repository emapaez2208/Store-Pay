package com.emapaez.storepay.features.user.domain;

import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "dni", source = "dni", qualifiedByName = "mapDniToEntity")
    @Mapping(target = "store", ignore = true)
    UserEntity toEntity(UserRequest request);

    @Mapping(target = "store", source = "store.name")
    @Mapping(target = "dni", source = "dni", qualifiedByName = "mapDniToDto")
    UserResponse toDto(UserEntity entity);


    @Named("mapDniToDto")
    default String mapDni(String dni){
        if(dni == null)
            return null;
        return dni.substring(0, 2) + "." +
                dni.substring(2, 5) + "." +
                dni.substring(5);
    }

    @Named("mapDniToEntity")
    default String mapDniToEntity(String dni){
        if(dni == null)
            return null;

        String limpio = dni.replaceAll("\\D", "");

        return String.format("%08d", Long.parseLong(limpio));
    }
}
