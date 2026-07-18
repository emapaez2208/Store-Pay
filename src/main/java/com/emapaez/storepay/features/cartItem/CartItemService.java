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

    private BigDecimal calculateSubTotal(CartItemEntity item){
        return item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
    }

    /// ------------------------- PRIVATE METHOD ---------------------------------------------- ///

    @Override
    public CartItemEntity getByExternalId(UUID externalId){
        return findByExternalId(externalId);
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
    public CartItemEntity create(CartItemRequest request){

        CartEntity cart = cartRepository.findByExternalId(request.cart()).orElseThrow(CartNotFoundException::new);
        StoreProductEntity storeProduct = storeProductRepository.findByExternalId(request.storeProduct()).orElseThrow(StoreProductNotFoundException::new);

        if(repository.existsByStoreProductAndCart(storeProduct, cart)){
            throw new CartItemExistsWithStoreProductAndCartException();
        }

        CartItemEntity item = mapper.toEntity(request);
        item.setCart(cart);
        item.setStoreProduct(storeProduct);
        item.setPrice(storeProduct.getPrice());
        item.setSubTotal(calculateSubTotal(item));

        CartItemEntity saved = repository.save(item);

        return saved;
    }

    @Override
    @Transactional
    public void updatePrice(UUID externalId, BigDecimal newPrice){
        CartItemEntity item = findByExternalId(externalId);
        item.setPrice(newPrice);
        item.setSubTotal(calculateSubTotal(item));

        repository.save(item);
    }


    @Override
    @Transactional
    public void updateQuantity(UUID externalId, Long quantity){

        if(quantity == null) {
            throw new IllegalArgumentException("Quantity is required.");
        }
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity must be greater than or equal to zero");
        }

        CartItemEntity item = findByExternalId(externalId);
        item.setQuantity(quantity);
        item.setSubTotal(calculateSubTotal(item));

        repository.save(item);
    }

    @Override
    public void delete(UUID externalId){
        CartItemEntity item = findByExternalId(externalId);

        repository.delete(item);
    }

    @Override
    public void deleteByCart(CartEntity cart){
        repository.deleteAllByCart(cart);
    }

}