package com.emapaez.storepay.features.cartItem;

import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.storeProduct.domain.StoreProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>, JpaSpecificationExecutor<CartItemEntity> {

    boolean existsByStoreProduct(StoreProductEntity storeProduct);
    boolean existsByStoreProductAndCart(StoreProductEntity storeProduct, CartEntity cart);
    Optional<CartItemEntity> findByExternalId(UUID externalId);

}
