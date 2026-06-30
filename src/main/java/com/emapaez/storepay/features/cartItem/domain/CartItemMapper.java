package com.emapaez.storepay.features.cartItem.domain;

import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;
import com.emapaez.storepay.features.product.domain.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartItemMapper {

    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "storeProduct", ignore = true)
    @Mapping(target = "price", ignore = true)
    CartItemEntity toEntity(CartItemRequest request);

    @Mapping(target = "product", source = "storeProduct.product.name")
    CartItemResponse toDto(CartItemEntity entity);
}
