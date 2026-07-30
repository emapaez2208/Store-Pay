package com.emapaez.storepay.features.store;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.store.domain.StoreEntity;
import com.emapaez.storepay.features.store.domain.StoreMapper;
import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import com.emapaez.storepay.features.store.domain.dto.StoreUpdate;
import com.emapaez.storepay.features.store.exception.StoreExistsWithCuitException;
import com.emapaez.storepay.features.store.exception.StoreExistsWithNameException;
import com.emapaez.storepay.features.store.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService{

    private final StoreRepository storeRepository;
    private final StoreMapper mapper;

    /// ---------------------------- PRIVATE METHOD ------------------------------------------ ///

    private StoreEntity findByExternalId(UUID externalId){
        return storeRepository.findByExternalId(externalId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with this external Id"));
    }

    /// ---------------------------- PRIVATE METHOD ------------------------------------------ ///

    @Override
    public PageResponse<StoreResponse> getAll(int page,
                                              int size,
                                              String name,
                                              String cuit,
                                              Boolean enable){

        PredicateSpecification<StoreEntity> spec = PredicateSpecification.allOf(
                StoreSpecification.nameContains(name),
                StoreSpecification.cuitEquals(cuit),
                StoreSpecification.enableEquals(enable)
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        return PageResponse.of(storeRepository.findAll(Specification.where(spec), pageable), mapper::toDto);
    }

    @Override
    @Transactional
    public StoreResponse create(StoreRequest request){

        if(storeRepository.existsByName(request.name())){
            throw new StoreExistsWithNameException();
        }
        if(storeRepository.existsByCuit(request.cuit())){
            throw new StoreExistsWithCuitException();
        }

        StoreEntity store = storeRepository.save(mapper.toEntity(request));

        return mapper.toDto(store);
    }


    @Override
    public StoreResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }


    @Override
    public void delete(UUID externalId){
        StoreEntity store = findByExternalId(externalId);

        store.setEnable(false);
        storeRepository.save(store);
    }


    @Override
    @Transactional
    public StoreResponse update(UUID externalId, StoreUpdate update){
        if(storeRepository.existsByName(update.name())){
            throw new StoreExistsWithNameException();
        }

        StoreEntity store = findByExternalId(externalId);
        store.setName(update.name());
        store.setDescription(update.description());

        StoreEntity saved = storeRepository.save(store);

        return mapper.toDto(saved);
    }
}
