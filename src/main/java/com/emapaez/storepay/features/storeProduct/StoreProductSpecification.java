package com.emapaez.storepay.features.storeProduct;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.PredicateSpecification;

import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;

public class StoreProductSpecification {

    public static PredicateSpecification<StoreProductEntity> priceBetween(BigDecimal min, BigDecimal max){
        return (root, cb) -> {
            if(min == null && max == null){
                return cb.conjunction();
            }else if(min == null){
                return cb.lessThanOrEqualTo(root.get("price"), max);
            }else if(max == null){
                return cb.greaterThanOrEqualTo(root.get("price"), min);
            }else{
                return cb.between(root.get("price"), min, max);
            }
        };
    }

    public static PredicateSpecification<StoreProductEntity> stockBetween(Long min, Long max){
        return (root, cb) -> {
            if(min == null && max == null){
                return cb.conjunction();
            }else if(max == null){
                return cb.greaterThanOrEqualTo(root.get("stock"), min);
            }else if(min == null){
                return cb.lessThanOrEqualTo(root.get("stock"), max);
            }else{
                return cb.between(root.get("stock"), min, max);
            }
        };
    }

    public static PredicateSpecification<StoreProductEntity> storeEquals(String store){
        return (root, cb) -> store == null || store.isBlank()
            ? cb.conjunction()
            : cb.equal(cb.lower(root.get("store").get("name")), store.toLowerCase());
    }

    public static PredicateSpecification<StoreProductEntity> productNameContains(String product){
        return (root, cb) -> product == null || product.isBlank()
            ? cb.conjunction()
            : cb.like(cb.lower(root.get("product").get("name")), "%" + product.toLowerCase() + "%");
    }

    public static PredicateSpecification<StoreProductEntity> enableEquals(Boolean enable){
        return (root, cb) -> enable == null
            ? cb.conjunction()
            : cb.equal(root.get("enable"), enable);
    }
    

}