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
import com.emapaez.storepay.features.user.UserRepository;
import com.emapaez.storepay.features.user.domain.UserEntity;
import com.emapaez.storepay.features.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService implements IStoreService{

    private final StoreRepository storeRepository;
    private final StoreMapper mapper;
    private final UserRepository userRepository;

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

        /// AL CREARLA BUSCAR EN LAS CREDENCIALES EL USUARIO QUE CREO Y AGREGARLO A LOS USUARIOS DE LA TIENDA

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

        StoreEntity store = findByExternalId(externalId);

        if(!store.getName().equalsIgnoreCase(update.name())){
            if(storeRepository.existsByName(update.name())){
                throw new StoreExistsWithNameException();
            }
            store.setName(update.name());
        }

        store.setDescription(update.description());

        StoreEntity saved = storeRepository.save(store);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public List<String> agreeUser(UUID storeId, UUID userId){
        StoreEntity store = findByExternalId(storeId);

        UserEntity user = userRepository.findByExternalId(userId)
                .orElseThrow(UserNotFoundException::new);

        user.addStore(store);

        userRepository.save(user);

        return store.getUsers().stream()
                .map(UserEntity::getName)
                .toList();
    }

    @Override
    @Transactional
    public List<String> removeUser(UUID storeId, UUID userId){
        StoreEntity store = findByExternalId(storeId);

        UserEntity user = userRepository.findByExternalId(userId)
                .orElseThrow(UserNotFoundException::new);

        user.removeStore(store);

        userRepository.save(user);

        return store.getUsers().stream()
                .map(UserEntity::getName)
                .toList();
    }
}
