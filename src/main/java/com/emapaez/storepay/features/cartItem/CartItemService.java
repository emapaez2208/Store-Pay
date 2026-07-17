package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cart.CartRepository;
import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.cart.exception.CartNotFoundException;
import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.cartItem.domain.CartItemMapper;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;
import com.emapaez.storepay.features.cartItem.exception.CartItemExistsWithStoreProductAndCartException;
import com.emapaez.storepay.features.cartItem.exception.CartItemNotFoundException;
import com.emapaez.storepay.features.storeProduct.StoreProductRepository;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import com.emapaez.storepay.features.storeProduct.exception.StoreProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{

    /// AL CREAR BUSCAR EL PRECIO EN EL PRODUCTO , MANEJAR EL STORE PRODUCT Y CART MANUALMENTE

    private final CartItemRepository repository;
    private final CartRepository cartRepository;
    private final StoreProductRepository storeProductRepository;
    private final CartItemMapper mapper;

    /// ------------------------- PRIVATE METHOD ---------------------------------------------- ///

    private CartItemEntity findByExternalId(UUID externalId){
        return repository.findByExternalId(externalId)
                .orElseThrow(CartItemNotFoundException::new);
    }

    /// ------------------------- PRIVATE METHOD ---------------------------------------------- ///

    @Override
    public CartItemResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }


    @Override
    public Page<CartItemResponse> getAll(int page,
                                         int size,
                                         Long quantityMin,
                                         Long quantityMax,
                                         BigDecimal priceMin,
                                         BigDecimal priceMax,
                                         String productName,
                                         String storeName,
                                         UUID cartId){

        PredicateSpecification<CartItemEntity> spec = PredicateSpecification.allOf(
                CartItemSpecification.quantityBetween(quantityMin, quantityMax),
                CartItemSpecification.priceBetween(priceMin, priceMax),
                CartItemSpecification.productNameContains(productName),
                CartItemSpecification.storeEquals(storeName),
                CartItemSpecification.cartEquals(cartId)
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by("cart.store.name").ascending()
                .and(Sort.by("storeProduct.product.name").ascending()));

        return repository.findAll(Specification.where(spec), pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public CartItemResponse create(CartItemRequest request){

        CartEntity cart = cartRepository.findByExternalId(request.cart()).orElseThrow(CartNotFoundException::new);
        StoreProductEntity storeProduct = storeProductRepository.findByExternalId(request.storeProduct()).orElseThrow(StoreProductNotFoundException::new);

        if(repository.existsByStoreProductAndCart(storeProduct, cart)){
            throw new CartItemExistsWithStoreProductAndCartException();
        }

        CartItemEntity item = mapper.toEntity(request);
        item.setCart(cart);
        item.setStoreProduct(storeProduct);
        item.setPrice(storeProduct.getPrice());

        CartItemEntity saved = repository.save(item);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CartItemResponse updatePrice(UUID externalId){
        CartItemEntity item = findByExternalId(externalId);
        item.setPrice(item.getStoreProduct().getPrice());

        CartItemEntity saved = repository.save(item);

        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public CartItemResponse updateQuantity(UUID externalId, Long quantity){

        if(quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity must be greater than or equal to zero");
        }

        CartItemEntity item = findByExternalId(externalId);
        item.setQuantity(quantity);

        CartItemEntity saved = repository.save(item);
        return mapper.toDto(saved);
    }

    @Override
    public void delete(UUID externalId){
        CartItemEntity item = findByExternalId(externalId);

        repository.delete(item);
    }

}