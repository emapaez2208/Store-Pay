package com.emapaez.storepay.features.store;

import com.emapaez.storepay.features.store.domain.StoreEntity;
import com.emapaez.storepay.features.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    Optional<StoreEntity> findByExternalId(UUID externalId);

    boolean existsByName(String name);

    boolean existsByCuit(String cuit);

    boolean existsByUsersContaining(UserEntity users);
}
