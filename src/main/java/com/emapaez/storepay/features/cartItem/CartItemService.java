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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /// TEST GIT


}