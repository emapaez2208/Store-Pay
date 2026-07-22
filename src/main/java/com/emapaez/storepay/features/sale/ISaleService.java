package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;
import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ISaleService {

    SaleResponse getByExternalId(UUID externalId);
    Page<SaleResponse> getByStore(int page, int size, UUID storeId);
    SaleResponse create(CartEntity cart);
    List<SaleItemResponse> getItems(UUID saleId);
}
