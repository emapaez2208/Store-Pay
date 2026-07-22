package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.features.sale.domain.SaleEntity;
import com.emapaez.storepay.features.sale.domain.SaleMapper;
import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;
import com.emapaez.storepay.features.sale.exception.SaleNotFoundException;
import com.emapaez.storepay.features.saleItem.ISaleItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
