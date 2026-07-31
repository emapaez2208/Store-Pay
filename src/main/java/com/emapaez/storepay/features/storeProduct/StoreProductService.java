package com.emapaez.storepay.features.storeProduct;

import java.math.BigDecimal;
import java.util.UUID;

import com.emapaez.storepay.common.model.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emapaez.storepay.features.cartItem.CartItemRepository;
import com.emapaez.storepay.features.product.ProductRepository;
import com.emapaez.storepay.features.product.domain.ProductEntity;
import com.emapaez.storepay.features.product.exception.ProductNotFoundException;
import com.emapaez.storepay.features.store.StoreRepository;
import com.emapaez.storepay.features.store.domain.StoreEntity;
import com.emapaez.storepay.features.store.exception.StoreNotFoundException;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductMapper;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductRequest;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductResponse;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductUpdate;
import com.emapaez.storepay.features.storeProduct.exception.StoreProductExistsInCartItemException;
import com.emapaez.storepay.features.storeProduct.exception.StoreProductExistsWithProductAndStoreException;
import com.emapaez.storepay.features.storeProduct.exception.StoreProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreProductService implements IStoreProductService{

    private final StoreProductRepository repository;
    private final StoreProductMapper mapper;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;


    /// ------------------------- PRIVATE METHOD -------------------------------------- ///
    
    private StoreProductEntity findByExternalId(UUID externalId){
        return repository.findByExternalId(externalId).orElseThrow(StoreProductNotFoundException::new);
    }
    
    /// ------------------------- PRIVATE METHOD -------------------------------------- ///
    

    @Override
    public PageResponse<StoreProductResponse> getAll(int page,
                                                     int size,
                                                     BigDecimal priceMin,
                                                     BigDecimal priceMax,
                                                     Long stockMin,
                                                     Long stockMax,
                                                     String store,
                                                     String product,
                                                     Boolean enable){

        PredicateSpecification<StoreProductEntity> spec = PredicateSpecification.allOf(
            StoreProductSpecification.priceBetween(priceMin, priceMax),
            StoreProductSpecification.stockBetween(stockMin, stockMax),
            StoreProductSpecification.storeEquals(store),
            StoreProductSpecification.productNameContains(product),
            StoreProductSpecification.enableEquals(enable)
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by("store.name").ascending()
                                                        .and(Sort.by("product.name").ascending()));

        return PageResponse.of(repository.findAll(Specification.where(spec), pageable), mapper::toDto);
    }


    @Override
    public StoreProductResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }


    @Override
    @Transactional
    public StoreProductResponse create(StoreProductRequest request){

        StoreEntity store = storeRepository.findByExternalId(request.store()).orElseThrow(StoreNotFoundException::new);
        ProductEntity product = productRepository.findByExternalId(request.product()).orElseThrow(ProductNotFoundException::new);

        if(repository.existsByProductAndStore(product, store))
            throw new StoreProductExistsWithProductAndStoreException();

        StoreProductEntity storeProduct = mapper.toEntity(request);
        storeProduct.setProduct(product);
        storeProduct.setStore(store);

        store.agreeProduct(storeProduct);

        StoreProductEntity saved = repository.save(storeProduct);

        return mapper.toDto(saved);
    }

    
    @Override
    @Transactional 
    public StoreProductResponse update(UUID externalId, StoreProductUpdate request){

        StoreProductEntity storeProduct = findByExternalId(externalId);
        storeProduct.setPrice(request.price());
        storeProduct.setStock(request.stock());

        StoreProductEntity saved = repository.save(storeProduct);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(UUID externalId){

        StoreProductEntity storeProduct = findByExternalId(externalId);

        if(cartItemRepository.existsByStoreProduct(storeProduct)){
            throw new StoreProductExistsInCartItemException("\"There is one or more cart item in this storeProduct. the storeProduct cannot be deleted.\"");
        }

        storeProduct.setEnable(false);
        repository.save(storeProduct);
    }



}
