package com.emapaez.storepay.features.cart;

import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.cart.domain.CartMapper;
import com.emapaez.storepay.features.cart.domain.dto.CartRequest;
import com.emapaez.storepay.features.cart.domain.dto.CartResponse;
import com.emapaez.storepay.features.cart.exception.CartNotFoundException;
import com.emapaez.storepay.features.cartItem.ICartItemService;
import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.exception.CartItemNotInCartException;
import com.emapaez.storepay.features.store.StoreRepository;
import com.emapaez.storepay.features.store.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private BigDecimal calculateSubTotal(CartEntity cart){

        return cart.getItems().stream()
                .map(CartItemEntity::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    private void calculatePrice(CartEntity cart){

        cart.setSubTotal(calculateSubTotal(cart));
        BigDecimal factor = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(cart.getDiscount()).divide(
                        BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
        );
        BigDecimal total = cart.getSubTotal().multiply(factor);
        cart.setTotalPrice(total);
    }

    private void clearCart(UUID cartId){
        CartEntity cart = findByExternalId(cartId);

        cartItemService.deleteByCart(cart);
        repository.delete(cart);
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
    public CartResponse agreeItem(UUID cartId, CartItemRequest itemRequest){
        CartEntity cart = findByExternalId(cartId);

        cart.addItem(cartItemService.create(itemRequest));
        calculatePrice(cart);

        CartEntity saved = repository.save(cart);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CartResponse removeItem(UUID cartId, UUID itemId){
        CartEntity cart = findByExternalId(cartId);
        CartItemEntity item = cartItemService.getByExternalId(itemId);

        if(!cart.getExternalId().equals(item.getCart().getExternalId())){
            throw new CartItemNotInCartException();
        }

        cart.removeItem(item);
        cartItemService.delete(itemId);
        CartEntity saved = repository.save(cart);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CartResponse updateItemQuantity(UUID cartId, UUID itemId, Long quantity){

        CartEntity cart = findByExternalId(cartId);
        CartItemEntity item = cartItemService.getByExternalId(itemId);

        if(!cart.getExternalId().equals(item.getCart().getExternalId())){
            throw new CartItemNotInCartException();
        }

        cartItemService.updateQuantity(itemId, quantity);
        calculatePrice(cart);

        CartEntity saved = repository.save(cart);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public CartResponse updateItemPrice(UUID cartId, UUID itemId, BigDecimal newPrice){

        CartEntity cart = findByExternalId(cartId);
        CartItemEntity item = cartItemService.getByExternalId(itemId);

        if(!cart.getExternalId().equals(item.getCart().getExternalId())){
            throw new CartItemNotInCartException();
        }

        cartItemService.updatePrice(itemId, newPrice);
        calculatePrice(cart);

        CartEntity saved = repository.save(cart);
        return mapper.toDto(saved);
    }


    /// FALTARIA CREAR LA VENTA GUARDARLA Y LUEGO ELIMINAR EL CARRITO

}
