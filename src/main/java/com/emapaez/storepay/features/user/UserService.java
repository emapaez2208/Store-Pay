package com.emapaez.storepay.features.user;

import com.emapaez.storepay.auth.credentials.CredentialsEntity;
import com.emapaez.storepay.auth.credentials.CredentialsRepository;
import com.emapaez.storepay.auth.credentials.exceptions.CredentialsNotFoundException;
import com.emapaez.storepay.auth.exception.ForbiddenException;
import com.emapaez.storepay.auth.permissions.RoleRepository;
import com.emapaez.storepay.auth.permissions.RolesEnum;
import com.emapaez.storepay.auth.providers.AuthenticatedUserProvider;
import com.emapaez.storepay.common.exception.EntityNotFoundCustomException;
import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.common.model.Password;
import com.emapaez.storepay.features.user.domain.UserEntity;
import com.emapaez.storepay.features.user.domain.UserMapper;
import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import com.emapaez.storepay.features.user.domain.dto.UserUpdate;
import com.emapaez.storepay.features.user.exception.UserExistsWithDniException;
import com.emapaez.storepay.features.user.exception.UserExistsWithEmailException;
import com.emapaez.storepay.features.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticatedUserProvider authenticatedUser;


    /// ------------------------ METHOD PRIVATE ------------------------------------- ///
    private UserEntity findByExternalId(UUID externalId){
        return userRepository.findByExternalId(externalId)
                .orElseThrow(() -> new UserNotFoundException("User not found with this External Id"));
    }

    @Transactional
    private void createCredentials(UserEntity user, RolesEnum role, Password password){

        CredentialsEntity credentials = CredentialsEntity.builder()
                .roles(Set.of(roleRepository.findByRole(role).orElseThrow( () -> new EntityNotFoundCustomException("Role not found"))))
                .enabled(true)
                .accountNonLocked(true)
                .username(user.getEmail().value())
                .externalId(user.getExternalId())
                .password(passwordEncoder.encode(password.value()))
                .user(user)
                .build();

        credentialsRepository.save(credentials);
    }
    /// ------------------------ METHOD PRIVATE ------------------------------------- ///

    @Override
    @Transactional
    public UserResponse create(UserRequest request){

        if(userRepository.existsByDni(request.dni()))
            throw new UserExistsWithDniException();

        if(userRepository.existsByEmail(request.email()))
            throw new UserExistsWithEmailException();

        if(request.role().equals(RolesEnum.ROLE_ADMIN)){
            throw new ForbiddenException("Only Admins can create admins.");
        }

        UserEntity user = userRepository.save(mapper.toEntity(request));

        createCredentials(user, request.role(), request.password());

        return mapper.toDto(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    @Transactional
    public UserResponse createAdmin(UserRequest request){

        if(userRepository.existsByDni(request.dni()))
            throw new UserExistsWithDniException();

        if(userRepository.existsByEmail(request.email()))
            throw new UserExistsWithEmailException();

        UserEntity user = userRepository.save(mapper.toEntity(request));

        createCredentials(user, request.role(), request.password());

        return mapper.toDto(user);
    }


    @Override
    public UserResponse getByExternalId(UUID externalId){
        return mapper.toDto(findByExternalId(externalId));
    }


    @Transactional
    @Override
    public void delete(UUID externalId){
        CredentialsEntity credentials = credentialsRepository.findByExternalId(externalId)
                .orElseThrow(CredentialsNotFoundException::new);

        credentials.setEnabled(false);
        credentialsRepository.save(credentials);
    }


    @Override
    public UserResponse update(UUID externalId, UserUpdate update){

        UserEntity user = findByExternalId(externalId);
        user.setName(update.name());
        user.setLastName(update.lastName());
        user.setPhoneNumber(update.phoneNumber());

        UserEntity saved = userRepository.save(user);

        return mapper.toDto(saved);
    }

    /// PRE AUTHORIZE ADMIN
    @Override
    public PageResponse<UserResponse> getAll(int page,
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

        Pageable pageable = PageRequest.of(page, size, Sort.by("stores").ascending());

        return PageResponse.of(userRepository.findAll(Specification.where(spec), pageable), mapper::toDto);
    }
}
