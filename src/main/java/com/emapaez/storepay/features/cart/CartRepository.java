package com.emapaez.storepay.features.cart;

import com.emapaez.storepay.features.cart.domain.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
