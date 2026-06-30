package com.emapaez.storepay.features.saleItem.domain;

import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {

    @Mapping(target = "product", source = "storeProduct.product.name")
    SaleItemResponse toDto(SaleItemEntity entity);
}
