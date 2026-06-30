package com.emapaez.storepay.features.productCategory.domain;

import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryRequest;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryEntity toEntity(ProductCategoryRequest request);

    ProductCategoryResponse toDto(ProductCategoryEntity entity);
}
