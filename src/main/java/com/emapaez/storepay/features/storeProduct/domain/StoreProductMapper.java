package com.emapaez.storepay.features.storeProduct.domain;

import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductRequest;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoreProductMapper {

    @Mapping(target = "store", ignore = true)
    @Mapping(target = "product", ignore = true)
    StoreProductEntity toEntity(StoreProductRequest request);

    @Mapping(target = "product", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "suggestedPrice", source = "product.suggestedPrice")
    @Mapping(target = "category", source = "product.productCategory.name")
    StoreProductResponse toDto(StoreProductEntity entity);


}
