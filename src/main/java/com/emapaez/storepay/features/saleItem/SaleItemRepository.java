package com.emapaez.storepay.features.saleItem;

import com.emapaez.storepay.features.saleItem.domain.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemEntity, Long> {

}
