package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryRequest;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-category")
public class ProductCategoryController {

    private final IProductCategoryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductCategoryResponse> getAll(@RequestParam int page,
                                                @RequestParam int size,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String description){
        return service.getAll(page, size, name, description);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryResponse create (@RequestBody @Valid ProductCategoryRequest request){
        return service.create(request);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategoryResponse update(@RequestBody @Valid ProductCategoryRequest request,
                                          @PathVariable String name){
        return service.update(name, request);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name){

        service.delete(name);
    }

}
