package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.features.productCategory.domain.ProductCategoryEntity;
import com.emapaez.storepay.features.productCategory.dto.ProductCategoryRequest;
import com.emapaez.storepay.features.productCategory.dto.ProductCategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {

    ProductCategoryEntity toEntity(ProductCategoryRequest request);

    ProductCategoryResponse toDto(ProductCategoryEntity entity);
}
