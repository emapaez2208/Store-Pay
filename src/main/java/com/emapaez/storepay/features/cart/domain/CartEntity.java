package com.emapaez.storepay.features.cart.domain;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", nullable = false, unique = true, updatable = false)
    private UUID externalId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) CHECK (total_price >= 0)")
    private BigDecimal totalPrice;

    @Column(nullable = false, columnDefinition = "INT CHECK (discount BETWEEN 0 AND 100)")
    private Integer discount;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItemEntity> items;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;


    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
        if(items == null)
            items = new ArrayList<>();
    }

}
