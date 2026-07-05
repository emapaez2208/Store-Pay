package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    boolean existsByStoreProduct(StoreProductEntity storeProduct);
}
