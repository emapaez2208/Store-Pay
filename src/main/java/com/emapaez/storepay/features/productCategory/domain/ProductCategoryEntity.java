package com.emapaez.storepay.features.productCategory.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, updatable = false, nullable = false)
    private UUID externalId;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(length = 200)
    private String description;

    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
    }
}
