package com.emapaez.storepay.features.storeProduct;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductRequest;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductResponse;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductUpdate;

public interface IStoreProductService {

    Page<StoreProductResponse> getAll(int page,
                                            int size,
                                            BigDecimal priceMin,
                                            BigDecimal priceMax,
                                            Long stockMin,
                                            Long stockMax,
                                            String store,
                                            String product,
                                            Boolean enable);
    StoreProductResponse getByExternalId(UUID externalId);
    StoreProductResponse create(StoreProductRequest request);
    StoreProductResponse update(UUID externalId, StoreProductUpdate request);
    void delete(UUID externalId);
}
