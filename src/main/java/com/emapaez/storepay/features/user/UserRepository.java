package com.emapaez.storepay.features.user;

import com.emapaez.storepay.common.model.Email;
import com.emapaez.storepay.features.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByExternalId(UUID externalId);

    boolean existsByEmail(Email email);

    boolean existsByDni(String dni);
}
