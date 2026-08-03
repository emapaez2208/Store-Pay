package com.emapaez.storepay.auth;

import com.emapaez.storepay.auth.credentials.CredentialsEntity;
import com.emapaez.storepay.auth.credentials.CredentialsRepository;
import com.emapaez.storepay.auth.credentials.exceptions.CredentialsNotFoundException;
import com.emapaez.storepay.auth.exception.InvalidRefreshTokenException;
import com.emapaez.storepay.auth.exception.RefreshTokenNotFoundException;
import com.emapaez.storepay.auth.dto.AuthRequest;
import com.emapaez.storepay.auth.dto.AuthResponse;
import com.emapaez.storepay.auth.exception.RefreshTokenExpiredException;
import com.emapaez.storepay.auth.jwt.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CredentialsRepository credentialsRepository;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;

    public UserDetails authenticate(AuthRequest input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.username(),
                        input.password()
                )
        );
        return credentialsRepository.findByUsername(input.username())
                .orElseThrow(() -> new CredentialsNotFoundException("User not found"));
    }

    @Transactional
    public String authenticateRefreshToken(CredentialsEntity user){
        String refreshToken = jwtService.generateRefreshToken(user);

        user.setRefreshToken(refreshToken);
        credentialsRepository.save(user);
        return refreshToken;
    }

    @Transactional
    public void logoutRefresh(String refreshToken){
        String username = jwtService.extractUsername(refreshToken);

        CredentialsEntity user = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new CredentialsNotFoundException("User not found"));

        user.setRefreshToken(null);
        credentialsRepository.save(user);
    }


    @Transactional
    public AuthResponse refreshAccessToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);

        CredentialsEntity user =
                credentialsRepository.findByUsername(username)
                        .orElseThrow(() -> new CredentialsNotFoundException("User not found"));

        if(user.getRefreshToken() == null){
            throw new RefreshTokenNotFoundException();
        }
        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new InvalidRefreshTokenException("The provided refresh token is invalid.");
        }

        if (!jwtService.validateRefreshToken(refreshToken, user)) {
            throw new RefreshTokenExpiredException();
        }

        String newAccessToken = jwtService.generateToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(newRefreshToken);
        credentialsRepository.save(user);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}