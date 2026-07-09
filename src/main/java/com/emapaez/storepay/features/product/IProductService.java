package com.emapaez.storepay.features.product;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;

public interface IProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse update(UUID externalId, ProductRequest request);
    ProductResponse findByExternalId(UUID externalId);
    void delete(UUID externalId);
    Page<ProductResponse> getAll(int page,
                                        int size,
                                        String name,
                                        String description,
                                        BigDecimal suggestedPriceMin,
                                        BigDecimal suggestedPriceMax,
                                        String productCategory);
}
