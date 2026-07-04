package com.emapaez.storepay.features.product;

import java.math.BigDecimal;

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

    public static PredicateSpecification<ProductEntity> suggestedPriceBetween(BigDecimal min, BigDecimal max){
        return (root, cb) -> {
            if(min == null && max == null){
                return cb.conjunction();

            }else if(max == null){
                return cb.greaterThanOrEqualTo(root.get("suggestedPrice"), min);
            
            }else if(min == null){
                return cb.lessThanOrEqualTo(root.get("suggestedPrice"), max);
            
            }else{
                return cb.between(root.get("suggestedPrice"), min, max);
            }
        };
    }

    public static PredicateSpecification<ProductEntity> enableEqual(Boolean enable){
        return (root, cb) -> enable == null
        ? cb.conjunction()
        : cb.equal(root.get("enable"), enable);
    }

    public static PredicateSpecification<ProductEntity> productCategoryEqual(String productCategory){
        return (root, cb) -> productCategory == null || productCategory.isBlank()
        ? cb.conjunction()
        : cb.equal(cb.lower(root.get("productCategory").get("name")), productCategory.toLowerCase());
    }

    
}
