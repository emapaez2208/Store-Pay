package com.emapaez.storepay.features.storeProduct;

import com.emapaez.storepay.features.product.domain.ProductEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProductEntity, Long> {

    boolean existsByProduct(ProductEntity product);
}
