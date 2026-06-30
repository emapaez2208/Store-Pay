package com.emapaez.storepay.features.storeProduct.domain;

import com.emapaez.storepay.features.product.domain.ProductEntity;
import com.emapaez.storepay.features.store.domain.StoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store_product")
public class StoreProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false, unique = true, updatable = false)
    private UUID externalId;

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) CHECK (price >= 0)")
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "BIGINT CHECK (stock >= 0)")
    private Long stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Boolean enable;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;



    @PrePersist
    void onCreate(){
        if(enable == null)
            enable = true;
    }

}
