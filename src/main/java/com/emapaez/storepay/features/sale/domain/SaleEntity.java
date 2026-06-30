package com.emapaez.storepay.features.sale.domain;

import com.emapaez.storepay.features.saleItem.domain.SaleItemEntity;
import com.emapaez.storepay.features.store.domain.StoreEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sale")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", updatable = false, unique = true, nullable = false)
    private UUID externalId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    private StoreEntity store;

    @Column(nullable = false, updatable = false,
            name = "total_price", precision = 10, scale =  2,
            columnDefinition = "DECIMAL(10,2) CHECK (total_price >= 0)")
    private BigDecimal totalPrice;

    @Column(nullable = false, updatable = false, columnDefinition = "INT CHECK (discount BETWEEN 0 AND 100)")
    private Integer discount;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    private List<SaleItemEntity> items;

    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
        if(items == null)
            items = new ArrayList<>();
    }
}
