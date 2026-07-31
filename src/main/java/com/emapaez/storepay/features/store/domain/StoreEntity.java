package com.emapaez.storepay.features.store.domain;

import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;
import com.emapaez.storepay.features.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private UUID externalId;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cuit;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean enable;

    @ManyToMany(mappedBy = "stores", fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<StoreProductEntity> products;

    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
        if(enable == null)
            enable = true;
        if(users == null)
            users = new ArrayList<>();
        if(products == null)
            products = new ArrayList<>();
    }

    public void agreeUser(UserEntity user){
        users.add(user);
    }

    public void removeUser(UserEntity user){
        users.remove(user);
    }

    public void agreeProduct(StoreProductEntity product){
        products.add(product);
    }
}
