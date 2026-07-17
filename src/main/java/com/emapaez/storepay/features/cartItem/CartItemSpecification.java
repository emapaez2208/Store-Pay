package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import org.springframework.data.jpa.domain.PredicateSpecification;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItemSpecification {

    public static PredicateSpecification<CartItemEntity> quantityBetween(Long min,Long max){
        return (root, cb) -> {
            if(min == null && max == null){
                return cb.conjunction();
            }else if(min == null){
                return cb.lessThanOrEqualTo(root.get("quantity"), max);
            }else if(max == null){
                return cb.greaterThanOrEqualTo(root.get("quantity"), min);
            }else{
                return cb.between(root.get("quantity"), min, max);
            }
        };
    }

    public static PredicateSpecification<CartItemEntity> priceBetween(BigDecimal min, BigDecimal max){
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

    public static PredicateSpecification<CartItemEntity> productNameContains(String product){
        return (root, cb) -> product == null || product.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("storeProduct").get("product").get("name")), "%" + product.toLowerCase() + "%");
    }

    public static PredicateSpecification<CartItemEntity> storeEquals(String store){
        return (root, cb) -> store == null || store.isBlank()
                ? cb.conjunction()
                : cb.equal(cb.lower(root.get("cart").get("store").get("name")), store.toLowerCase());
    }

    public static PredicateSpecification<CartItemEntity> cartEquals(UUID externalId){
        return (root, cb) -> externalId == null
                ? cb.conjunction()
                : cb.equal(root.get("cart").get("externalId"), externalId);
    }
}
