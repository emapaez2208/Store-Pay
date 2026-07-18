package com.emapaez.storepay.features.cart;

import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.cart.domain.CartMapper;
import com.emapaez.storepay.features.cart.domain.dto.CartRequest;
import com.emapaez.storepay.features.cart.domain.dto.CartResponse;
import com.emapaez.storepay.features.cart.exception.CartNotFoundException;
import com.emapaez.storepay.features.cartItem.CartItemRepository;
import com.emapaez.storepay.features.cartItem.ICartItemService;
import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.exception.CartItemNotFoundException;
import com.emapaez.storepay.features.store.StoreRepository;
import com.emapaez.storepay.features.store.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final CartRepository repository;
    private final CartMapper mapper;
    private final StoreRepository storeRepository;
    private final ICartItemService cartItemService;

    /// ------------------------------ PRIVATE METHOD ------------------------------------ ///

    private CartEntity findByExternalId(UUID externalId){
        return repository.findByExternalId(externalId)
                .orElseThrow(CartNotFoundException::new);
    }
    /// ------------------------------ PRIVATE METHOD ------------------------------------ ///

    @Override
    public CartResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }


    @Override
    public List<CartResponse> getByStore(UUID storeId){
        return repository.findByStoreExternalId(storeId).stream()
                .map(mapper::toDto).toList();
    }


    @Override
    @Transactional
    public CartResponse create(CartRequest request){

        CartEntity cart = mapper.toEntity(request);
        cart.setStore(storeRepository.findByExternalId(request.store())
                .orElseThrow(StoreNotFoundException::new));

        CartEntity saved = repository.save(cart);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CartResponse agreeItem(CartItemRequest itemRequest){
        CartEntity cart = findByExternalId(itemRequest.cart());

        cart.addItem(cartItemService.create(itemRequest));

        /// SUMAR PRECIO TOTAL

        CartEntity saved = repository.save(cart);
        return mapper.toDto(saved);
    }



    /// MANEJAR EL STORE MANUALMENTE AL RECIBIR UN REQUEST, EL PRECIO TOTAL Y LA LISTA DE ITEMS TAMBIEN
}
