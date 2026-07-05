package com.emapaez.storepay.features.storeProduct;

import com.emapaez.storepay.features.product.domain.ProductEntity;
import com.emapaez.storepay.features.store.domain.StoreEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface StoreProductRepository extends JpaRepository<StoreProductEntity, Long>, JpaSpecificationExecutor<StoreProductEntity> {

    boolean existsByProduct(ProductEntity product);

    boolean existsByProductAndStore(ProductEntity product, StoreEntity store);

    Optional<StoreProductEntity> findByExternalId(UUID externalId);
}
