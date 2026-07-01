package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.features.productCategory.domain.ProductCategoryEntity;
import org.springframework.data.jpa.domain.PredicateSpecification;

public class ProductCategorySpecification {

    public static PredicateSpecification<ProductCategoryEntity> nameContains(String name){
        return (root, cb) -> name == null || name.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static PredicateSpecification<ProductCategoryEntity> descriptionContains(String description){
        return (root, cb) -> description == null || description.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }
}
