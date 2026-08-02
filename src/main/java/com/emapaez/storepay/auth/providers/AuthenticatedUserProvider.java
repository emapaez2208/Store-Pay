package com.emapaez.storepay.auth.providers;

import com.emapaez.storepay.auth.dto.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserProvider {

    public AuthUser getCurrentUser(){
        return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
