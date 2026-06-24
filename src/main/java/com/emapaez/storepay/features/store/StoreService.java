package com.emapaez.storepay.features.store;

import com.emapaez.storepay.features.store.domain.StoreEntity;
import com.emapaez.storepay.features.store.domain.StoreMapper;
import com.emapaez.storepay.features.store.domain.dto.StoreRequest;
import com.emapaez.storepay.features.store.domain.dto.StoreResponse;
import com.emapaez.storepay.features.store.exception.StoreExistsWithCuitException;
import com.emapaez.storepay.features.store.exception.StoreExistsWithNameException;
import com.emapaez.storepay.features.store.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService{

    private final StoreRepository storeRepository;
    private final StoreMapper mapper;

    private StoreEntity getByExternalId(UUID externalId){
        return storeRepository.findByExternalId(externalId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with this external Id"));
    }

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



    public StoreResponse findByExternalId(UUID externalId){
        return mapper.toDto(getByExternalId(externalId));
    }

    public void delete(UUID externalId){
        StoreEntity store = getByExternalId(externalId);

        store.setEnable(false);
        storeRepository.save(store);
    }
}
