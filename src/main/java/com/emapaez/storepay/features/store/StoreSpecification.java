package com.emapaez.storepay.features.store;

import com.emapaez.storepay.features.store.domain.StoreEntity;
import org.springframework.data.jpa.domain.PredicateSpecification;

public class StoreSpecification {

    public static PredicateSpecification<StoreEntity> nameContains(String name){
        return(root, cb) -> name == null || name.isBlank()
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")),"%" + name.toLowerCase() + "%");
    }

    public static PredicateSpecification<StoreEntity> cuitEquals(String cuit){
        return (root, cb) -> cuit == null || cuit.isBlank()
                ? cb.conjunction()
                : cb.equal(cb.lower(root.get("cuit")), cuit.toLowerCase());
    }

    public static PredicateSpecification<StoreEntity> enableEquals(Boolean enable){
        return (root, cb) -> enable == null
                ? cb.conjunction()
                : cb.equal(root.get("enable"), enable);
    }
}
