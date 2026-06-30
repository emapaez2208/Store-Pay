package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
