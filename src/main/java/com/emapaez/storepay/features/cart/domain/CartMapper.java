package com.emapaez.storepay.features.cart.domain;

import com.emapaez.storepay.features.cart.domain.dto.CartRequest;
import com.emapaez.storepay.features.cart.domain.dto.CartResponse;
import com.emapaez.storepay.features.cartItem.domain.CartItemMapper;
import com.emapaez.storepay.features.store.domain.StoreMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StoreMapper.class, CartItemMapper.class})
public interface CartMapper {

    @Mapping(target = "store", ignore = true)
    CartEntity toEntity (CartRequest request);

    CartResponse toDto(CartEntity entity);
}
