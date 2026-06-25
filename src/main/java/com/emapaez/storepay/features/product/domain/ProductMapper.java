package com.emapaez.storepay.features.product.domain;

import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "productCategory", ignore = true)
    ProductEntity toEntity(ProductRequest request);

    @Mapping(target = "category", source = "productCategory.name")
    ProductResponse toDto(ProductEntity entity);
}
