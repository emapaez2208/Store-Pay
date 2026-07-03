package com.emapaez.storepay.features.product;

import org.springframework.data.jpa.domain.PredicateSpecification;

import com.emapaez.storepay.features.product.domain.ProductEntity;

public class ProductSpecification {


    public static PredicateSpecification<ProductEntity> nameContains(String name){
        return (root, cb) -> name == null || name.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static PredicateSpecification<ProductEntity> descriptionContains(String description){
        return (root, cb) -> description == null || description.isBlank()
        ? cb.conjunction()
        : cb.like(cb.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    
}
