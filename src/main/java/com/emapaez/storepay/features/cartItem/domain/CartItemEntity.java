package com.emapaez.storepay.features.cartItem.domain;

import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item")
public class CartItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "external_id", nullable = false, unique = true, updatable = false)
    private UUID externalId;

    @Column(nullable = false, columnDefinition = "BIGINT CHECK (quantity >= 0)")
    private Long quantity;

    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2) CHECK (price >= 0)")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private StoreProductEntity storeProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;


    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
    }
}
