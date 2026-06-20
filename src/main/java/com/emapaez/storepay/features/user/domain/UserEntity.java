package com.emapaez.storepay.features.user.domain;

import com.emapaez.storepay.common.model.Email;
import com.emapaez.storepay.features.store.domain.StoreEntity;
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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private UUID externalId;

    @Column(nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String dni;

    @Embedded
    @Column(nullable = false, unique = true)
    private Email email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @OneToMany
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;
}
