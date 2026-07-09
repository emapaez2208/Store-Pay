package com.emapaez.storepay.features.storeProduct;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductRequest;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductResponse;
import com.emapaez.storepay.features.storeProduct.domain.dto.StoreProductUpdate;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store-product")
public class StoreProductController {

    private final IStoreProductService service;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StoreProductResponse> getAll(@RequestParam int page,
                                            @RequestParam int size,
                                            @RequestParam(required = false) BigDecimal priceMin,
                                            @RequestParam(required = false) BigDecimal priceMax,
                                            @RequestParam(required = false) Long stockMin,
                                            @RequestParam(required = false) Long stockMax,
                                            @RequestParam(required = false) String store,
                                            @RequestParam(required = false) String product,
                                            @RequestParam(required = false) Boolean enable){


        return service.getAll(page, size, priceMin, priceMax, stockMin, stockMax, store, product, enable);
    }


    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public StoreProductResponse getByExternalId(@PathVariable UUID externalId){
        return service.getByExternalId(externalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreProductResponse create(@RequestBody @Valid StoreProductRequest request){
        
        return service.create(request);
    }


    @PutMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public StoreProductResponse update(@PathVariable UUID externalId, @RequestBody @Valid StoreProductUpdate request){

        return service.update(externalId, request);
    }

    @DeleteMapping("/{externalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID externalId){

        service.delete(externalId);
    }
}
