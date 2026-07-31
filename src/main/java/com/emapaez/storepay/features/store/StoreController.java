package com.emapaez.storepay.features.store;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {

    private final IStoreService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<StoreResponse> getAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String cuit,
                                              @RequestParam(required = false) Boolean enable){
        return service.getAll(page, size, name, cuit, enable);
    }

    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponse getByExternalId(@PathVariable UUID externalId){
        return service.getByExternalId(externalId);
    }

    @DeleteMapping("/{externalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID externalId){
        service.delete(externalId);
    }

    @PutMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public StoreResponse update(@PathVariable UUID externalId, @RequestBody @Valid StoreUpdate request){
        return service.update(externalId, request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreResponse create(@RequestBody @Valid StoreRequest request){
        return service.create(request);
    }

    @PostMapping("/{externalId}/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> agreeUser(@PathVariable UUID externalId, @PathVariable UUID userId){
        return service.agreeUser(externalId, userId);
    }

    @DeleteMapping("/{externalId}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<String> removeUser(@PathVariable UUID externalId, @PathVariable UUID userId){
        return service.removeUser(externalId, userId);
    }
}
