package com.emapaez.storepay.features.product;

import java.util.UUID;

import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;

public interface IProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse update(UUID externalId, ProductRequest request);
    ProductResponse findByExternalId(UUID externalId);
    void delete(UUID externalId);
}
