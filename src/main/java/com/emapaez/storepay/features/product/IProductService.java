package com.emapaez.storepay.features.product;

import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;

public interface IProductService {
    ProductResponse create(ProductRequest request);
}
