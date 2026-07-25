package com.emapaez.storepay.features.product;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final IProductService productService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ProductResponse> getAll(@RequestParam int page,
                                                @RequestParam int size,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String description,
                                                @RequestParam(required = false) BigDecimal suggestedPriceMin,
                                                @RequestParam(required = false) BigDecimal suggestedPriceMax,
                                                @RequestParam(required = false) String productCategory){

        return productService.getAll(page, size, name, description, suggestedPriceMin, suggestedPriceMax, productCategory);
    }

    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getByExternalId(@PathVariable UUID externalId){
        return productService.findByExternalId(externalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid ProductRequest request){
        return productService.create(request);
    }

    @PutMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable UUID externalId,
                                  @RequestBody @Valid ProductRequest request){
        return productService.update(externalId, request);
    }

    @DeleteMapping("/{externalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID externalId){
        productService.delete(externalId);
    }
}
