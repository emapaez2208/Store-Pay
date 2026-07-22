package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.features.sale.domain.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    Optional<SaleEntity> findByExternalId(UUID externalId);

    Page<SaleEntity> findByStoreExternalId(UUID storeExternalId, Pageable pageable);
}