package com.emapaez.storepay.features.sale;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.sale.domain.dto.SaleResponse;
import com.emapaez.storepay.features.saleItem.domain.dto.SaleItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final ISaleService service;

    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public SaleResponse getByExternalId(@PathVariable UUID externalId){
        return service.getByExternalId(externalId);
    }

    @GetMapping("/store/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<SaleResponse> getByStore(@RequestParam int page,
                                                 @RequestParam int size,
                                                 @PathVariable UUID externalId){
        return service.getByStore(page, size, externalId);
    }

    @GetMapping("/{externalId}/items")
    @ResponseStatus(HttpStatus.OK)
    public List<SaleItemResponse> getItems(@PathVariable UUID externalId){
        return service.getItems(externalId);
    }
}
