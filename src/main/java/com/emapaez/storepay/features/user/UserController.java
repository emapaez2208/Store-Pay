package com.emapaez.storepay.features.user;

import com.emapaez.storepay.common.model.PageResponse;
import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import com.emapaez.storepay.features.user.domain.dto.UserUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<UserResponse> getAll(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String dni,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) Long phoneNumber,
                                             @RequestParam(required = false) String store,
                                             @RequestParam(required = false) Boolean enable){
        return service.getAll(page, size, name, lastName, dni, email, phoneNumber, store, enable);
    }

    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getByExternalId(@PathVariable UUID externalId){
        return service.getByExternalId(externalId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Valid UserRequest request){
        return service.create(request);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createAdmin(@RequestBody @Valid UserRequest request){
        return service.createAdmin(request);
    }

    @DeleteMapping("/{externalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID externalId){
        service.delete(externalId);
    }

    @PutMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse update(@PathVariable UUID externalId, @RequestBody @Valid UserUpdate request){
        return service.update(externalId, request);
    }

}
