package com.emapaez.storepay.features.sale.domain;

import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;
import com.emapaez.storepay.features.saleItem.domain.SaleItemMapper;
import com.emapaez.storepay.features.store.domain.StoreMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    @Mapping(target = "storeId", source = "store.externalId")
    @Mapping(target = "storeName", source = "store.name")
    SaleResponse toDto(SaleEntity entity);
}
