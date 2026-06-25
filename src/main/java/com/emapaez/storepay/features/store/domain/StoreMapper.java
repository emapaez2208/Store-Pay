package com.emapaez.storepay.features.store.domain;

import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    @Mapping(target = "cuit", source = "cuit", qualifiedByName = "mapCuitToEntity")
    StoreEntity toEntity(StoreRequest request);

    @Mapping(target = "cuit", qualifiedByName = "mapCuitToDto")
    StoreResponse toDto(StoreEntity entity);


    @Named("mapCuitToDto")
    default String mapCuit(String cuit){
        if(cuit == null)
            return null;
        return cuit.substring(0, 2) + "-"
                + cuit.substring(2, 10) + "-"
                + cuit.substring(10);
    }

    @Named("mapCuitToEntity")
    default String mapCuitToEntity(String cuit){
        if(cuit == null)
            return null;

        return cuit.replaceAll("\\D", "");
    }
}
