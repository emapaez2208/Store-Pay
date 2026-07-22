package com.emapaez.storepay.features.cart;

import com.emapaez.storepay.features.cart.domain.dto.CartRequest;
import com.emapaez.storepay.features.cart.domain.dto.CartResponse;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final ICartService service;


    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse getByExternalId(@PathVariable UUID externalId){
        return service.getByExternalId(externalId);
    }

    @GetMapping("/store/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse> getByStore(@PathVariable UUID externalId){
        return service.getByStore(externalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse create(@RequestBody @Valid CartRequest request){
        return service.create(request);
    }

    @PutMapping("/{externalId}/item")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse agreeItem(@PathVariable UUID externalId, @RequestBody @Valid CartItemRequest request){
        return service.agreeItem(externalId, request);
    }

    @DeleteMapping("/{externalId}/item/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CartResponse removeItem(@PathVariable UUID externalId, @PathVariable UUID itemId){
        return service.removeItem(externalId, itemId);
    }



}
