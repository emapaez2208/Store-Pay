package com.emapaez.storepay.features.product;

import com.emapaez.storepay.features.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByProductCategoryName(String name);
}
