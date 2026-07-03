package com.emapaez.storepay.features.product;

import com.emapaez.storepay.features.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByProductCategoryNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

    Optional<ProductCategoryEntity> findByExternalId(UUID externalId);

}
