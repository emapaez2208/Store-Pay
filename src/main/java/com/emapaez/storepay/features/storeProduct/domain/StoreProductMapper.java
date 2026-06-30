package com.emapaez.storepay.features.storeProduct.domain;

import com.emapaez.storepay.features.product.domain.ProductMapper;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductRequest;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface StoreProductMapper {

    @Mapping(target = "store", ignore = true)
    @Mapping(target = "product", ignore = true)
    StoreProductEntity toEntity(StoreProductRequest request);

    StoreProductResponse toDto(StoreProductEntity entity);


}
