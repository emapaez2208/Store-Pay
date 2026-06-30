package com.emapaez.storepay.features.saleItem.domain;

import com.emapaez.storepay.features.sale.domain.SaleEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sale_item")
public class SaleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true, name = "external_id")
    private UUID externalId;

    @Column(nullable = false, updatable = false, columnDefinition = "BIGINT CHECK (quantity >= 0)")
    private Long quantity;

    @Column(nullable = false, updatable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) CHECK (price >= 0)")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_product_id", nullable = false, updatable = false)
    private StoreProductEntity storeProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id", nullable = false, updatable = false)
    private SaleEntity sale;


    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
    }
}
