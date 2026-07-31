package com.emapaez.storepay.features.user.domain;

import com.emapaez.storepay.common.model.Email;
import com.emapaez.storepay.features.store.domain.StoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private UUID externalId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Embedded
    @Column(nullable = false, unique = true)
    private Email email;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "store_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private List<StoreEntity> stores;

    @PrePersist
    void onCreate(){
        if(externalId == null)
            externalId = UUID.randomUUID();
        if(stores == null)
            stores = new ArrayList<>();
    }

    public void addStore(StoreEntity store){
        stores.add(store);
        store.agreeUser(this);
    }

    public void removeStore(StoreEntity store){
        stores.remove(store);
        store.removeUser(this);
    }
}
