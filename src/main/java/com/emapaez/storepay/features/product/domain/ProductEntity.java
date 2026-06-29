package com.emapaez.storepay.features.product.domain;

import com.emapaez.storepay.features.productCategory.domain.ProductCategoryEntity;
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
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, updatable = false, nullable = false)
    private UUID externalId;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2, name = "suggested_price")
    private BigDecimal suggestedPrice;

    @Column(nullable = false)
    private Boolean enable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategoryEntity productCategory;


    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
        if(enable == null)
            enable = true;
    }
}
