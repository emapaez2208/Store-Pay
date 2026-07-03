package com.emapaez.storepay.features.product;

import com.emapaez.storepay.features.product.domain.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    boolean existsByProductCategoryNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

    Optional<ProductEntity> findByExternalId(UUID externalId);

}
