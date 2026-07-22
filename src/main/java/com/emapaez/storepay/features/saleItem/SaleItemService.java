package com.emapaez.storepay.features.saleItem;

import com.emapaez.storepay.features.cartItem.domain.CartItemEntity;
import com.emapaez.storepay.features.sale.domain.SaleEntity;
import com.emapaez.storepay.features.saleItem.domain.SaleItemEntity;
import com.emapaez.storepay.features.saleItem.domain.SaleItemMapper;
import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;
import com.emapaez.storepay.features.saleItem.exception.SaleItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleItemService implements ISaleItemService {

    private final SaleItemRepository repository;
    private final SaleItemMapper mapper;

    /// --------------------------------- PRIVATE METHOD ------------------------------------ ///

    private SaleItemEntity findByExternalId(UUID externalId){
        return repository.findByExternalId(externalId)
                .orElseThrow(SaleItemNotFoundException::new);
    }

    /// --------------------------------- PRIVATE METHOD ------------------------------------ ///

    @Override
    public List<SaleItemResponse> getBySale(SaleEntity sale){
        return repository.findBySale(sale).stream().map(mapper::toDto).toList();
    }

    @Override
    public SaleItemResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }

    @Override
    @Transactional
    public Boolean create(SaleEntity sale, CartItemEntity item){

        SaleItemEntity saleItem = SaleItemEntity.builder()
                .sale(sale)
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .subTotal(item.getSubTotal())
                .storeProduct(item.getStoreProduct())
                .build();

        repository.save(saleItem);
        return Boolean.TRUE;
    }



}
