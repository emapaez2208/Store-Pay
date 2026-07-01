package com.emapaez.storepay.features.user;

import com.emapaez.storepay.features.user.domain.UserEntity;
import com.emapaez.storepay.features.user.domain.UserMapper;
import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import com.emapaez.storepay.features.user.domain.dto.UserUpdate;
import com.emapaez.storepay.features.user.exception.UserExistsWithDniException;
import com.emapaez.storepay.features.user.exception.UserExistsWithEmailException;
import com.emapaez.storepay.features.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    /// ------------------------ METHOD PRIVATE ------------------------------------- ///
    private UserEntity getByExternalId(UUID externalId){
        return userRepository.findByExternalId(externalId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this External Id"));
    }
    /// ------------------------ METHOD PRIVATE ------------------------------------- ///

    @Override
    @Transactional
    public UserResponse create(UserRequest request){

        if(userRepository.existsByDni(request.dni()))
            throw new UserExistsWithDniException();

        if(userRepository.existsByEmail(request.email()))
            throw new UserExistsWithEmailException();

        UserEntity user = userRepository.save(mapper.toEntity(request));

        return mapper.toDto(user);
    }


    @Override
    public UserResponse findByExternalId(UUID externalId){
        return mapper.toDto(getByExternalId(externalId));
    }


    @Override
    public void delete(UUID externalId){
        /// CREDENTIAL DELETION LOGIC
    }


    @Override
    public UserResponse update(UUID externalId, UserUpdate update){

        UserEntity user = getByExternalId(externalId);
        user.setName(update.name());
        user.setLastName(update.lastName());
        user.setPhoneNumber(update.phoneNumber());

        UserEntity saved = userRepository.save(user);

        return mapper.toDto(saved);
    }

    /// PRE AUTHORIZE ADMIN
    @Override
    public Page<UserResponse> getAll(int page,
                                    int size,
                                    String name,
                                    String lastName,
                                    String dni,
                                    String email,
                                    Long phoneNumber,
                                    String store,
                                    Boolean enable){

        PredicateSpecification<UserEntity> spec = PredicateSpecification.allOf(
                UserSpecification.nameContains(name),
                UserSpecification.lastNameContains(lastName),
                UserSpecification.dniEquals(dni),
                UserSpecification.emailEquals(email),
                UserSpecification.phoneEquals(phoneNumber),
                UserSpecification.storeEquals(store)
                /// AGREGAR ENABLE CON CREDENCIALES TAMBIEN EN USERSPECIFICATION
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by("store").ascending());

        return userRepository.findAll(Specification.where(spec), pageable)
                .map(mapper::toDto);
    }
}
