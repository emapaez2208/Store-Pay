package com.emapaez.storepay.features.saleItem;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.sale.domain.SaleEntity;
import com.emapaez.storepay.features.saleItem.domain.SaleItemEntity;
import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;

import java.util.List;
import java.util.UUID;

public interface ISaleItemService {

    List<SaleItemResponse> getBySale(SaleEntity sale);
    SaleItemResponse getByExternalId(UUID externalId);
    List<SaleItemEntity> create(SaleEntity sale, List<CartItemEntity> items);
}
