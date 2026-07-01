package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryRequest;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryResponse;
import org.springframework.data.domain.Page;

public interface IProductCategoryService {
    Page<ProductCategoryResponse> getAll(int page, int size, String name, String description);
    ProductCategoryResponse update(String oldName, ProductCategoryRequest request);
    ProductCategoryResponse create(ProductCategoryRequest request);
    void delete(String name);

}
