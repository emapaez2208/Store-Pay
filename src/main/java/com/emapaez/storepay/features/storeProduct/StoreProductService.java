package com.emapaez.storepay.features.storeProduct;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductMapper;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductResponse;
import com.emapaez.storepay.features.storeProduct.exception.StoreProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreProductService implements IStoreProductService{

    private final StoreProductRepository repository;
    private final StoreProductMapper mapper;


    /// ------------------------- PRIVATE METHOD -------------------------------------- ///
    
    private StoreProductEntity findByExternalId(UUID externalId){
        return repository.findByExternalId(externalId).orElseThrow(StoreProductNotFoundException::new);
    }
    
    /// ------------------------- PRIVATE METHOD -------------------------------------- ///
    

    public Page<StoreProductResponse> getAll(int page,
                                            int size,
                                            BigDecimal priceMin,
                                            BigDecimal priceMax,
                                            Long stockMin,
                                            Long stockMax,
                                            String store,
                                            String product,
                                            Boolean enable){

        return null;
    }
}
