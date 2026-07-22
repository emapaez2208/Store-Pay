package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;

import java.util.UUID;

public interface ISaleService {

    SaleResponse getByExternalId(UUID externalId);

}
