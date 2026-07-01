package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.features.productCategory.domain.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long>, JpaSpecificationExecutor<ProductCategoryEntity> {

    Optional<ProductCategoryEntity> findByName(String name);
    boolean existsByName(String name);
}
