package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cartItem.domain.dto.CartItemRequest;
import com.emapaez.storepay.features.cartItem.domain.dto.CartItemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart/cart-item")
public class CartItemController {

    private final ICartItemService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CartItemResponse> getAll (@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam(required = false) Long quantityMin,
                                          @RequestParam(required = false) Long quantityMax,
                                          @RequestParam(required = false) BigDecimal priceMin,
                                          @RequestParam(required = false) BigDecimal priceMax,
                                          @RequestParam(required = false) String productName,
                                          @RequestParam(required = false) String storeName,
                                          @RequestParam(required = false) UUID cartId){
        return service.getAll(page, size, quantityMin, quantityMax, priceMin, priceMax, productName, storeName, cartId);
    }

    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public CartItemResponse getByExternalId(@PathVariable UUID externalId){
        return service.getByExternalId(externalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponse create(@RequestBody @Valid CartItemRequest request){
        return service.create(request);
    }

    @PatchMapping("/{externalId}/quantity")
    @ResponseStatus(HttpStatus.OK)
    public CartItemResponse updateQuantity(@PathVariable UUID externalId, @RequestBody Map<String, Long> body){
        return service.updateQuantity(externalId, body.get("quantity"));
    }

    @PatchMapping("/{externalId}/price")
    @ResponseStatus(HttpStatus.OK)
    public CartItemResponse updatePrice(@PathVariable UUID externalId){
        return service.updatePrice(externalId);
    }

    @DeleteMapping("/{externalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID externalId){
        service.delete(externalId);
    }
}
