package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.cart.domain.CartEntity;
import com.emapaez.storepay.features.sale.domain.SaleEntity;
import com.emapaez.storepay.features.sale.domain.SaleMapper;
import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;
import com.emapaez.storepay.features.sale.exception.SaleNotFoundException;
import com.emapaez.storepay.features.saleItem.ISaleItemService;
import com.emapaez.storepay.features.saleItem.domain.SaleItemEntity;
import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final SaleRepository repository;
    private final SaleMapper mapper;
    private final ISaleItemService saleItemService;

    /// -------------------------- PRIVATE METHOD --------------------------------------------- ///

    private SaleEntity findByExternalId(UUID externalId){
        return repository.findByExternalId(externalId)
                .orElseThrow(SaleNotFoundException::new);
    }

    /// -------------------------- PRIVATE METHOD --------------------------------------------- ///

    @Override
    public SaleResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }


    @Override
    public PageResponse<SaleResponse> getByStore(int page, int size, UUID storeId){

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return PageResponse.of(repository.findByStoreExternalId(storeId, pageable), mapper::toDto);
    }

    @Override
    @Transactional
    public SaleResponse create(CartEntity cart){

        SaleEntity sale = SaleEntity.builder()
                .store(cart.getStore())
                .discount(cart.getDiscount())
                .subTotal(cart.getSubTotal())
                .totalPrice(cart.getTotalPrice())
                .build();

        SaleEntity saved = repository.save(sale);

        List<SaleItemEntity> items = saleItemService.create(saved, cart.getItems());
        saved.setItems(items);

        return mapper.toDto(saved);
    }

    @Override
    public List<SaleItemResponse> getItems(UUID saleId){

        SaleEntity sale = findByExternalId(saleId);
        return saleItemService.getBySale(sale);
    }

    /// ADD GET MY STORE WITH CREDENTIALS
}
