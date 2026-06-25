package com.emapaez.storepay.features.store.domain;

import com.emapaez.storepay.features.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<UserEntity> users;



    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
        if(enable == null)
            enable = true;
    }
}
