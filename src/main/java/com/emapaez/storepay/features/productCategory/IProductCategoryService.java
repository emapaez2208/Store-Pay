package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryRequest;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryResponse;

public interface IProductCategoryService {
    PageResponse<ProductCategoryResponse> getAll(int page, int size, String name, String description);
    ProductCategoryResponse update(String oldName, ProductCategoryRequest request);
    ProductCategoryResponse create(ProductCategoryRequest request);
    void delete(String name);

}
