package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.features.sale.domain.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

}
