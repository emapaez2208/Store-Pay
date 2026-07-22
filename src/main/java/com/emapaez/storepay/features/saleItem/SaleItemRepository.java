package com.emapaez.storepay.features.saleItem;

import com.emapaez.storepay.features.sale.domain.SaleEntity;
import com.emapaez.storepay.features.saleItem.domain.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemEntity, Long> {

    Optional<SaleItemEntity> findByExternalId(UUID externalId);
    List<SaleItemEntity> findBySale(SaleEntity sale);
}
