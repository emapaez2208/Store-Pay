package com.emapaez.storepay.features.sale.domain;

import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;
import com.emapaez.storepay.features.saleItem.domain.SaleItemMapper;
import com.emapaez.storepay.features.store.domain.StoreMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SaleItemMapper.class, StoreMapper.class})
public interface SaleMapper {

    SaleResponse toDto(SaleEntity entity);
}
