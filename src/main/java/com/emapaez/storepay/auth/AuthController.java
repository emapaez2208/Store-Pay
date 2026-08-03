package com.emapaez.storepay.auth;

import com.emapaez.storepay.auth.credentials.CredentialsEntity;
import com.emapaez.storepay.auth.dto.AuthRequest;
import com.emapaez.storepay.auth.dto.AuthResponse;
import com.emapaez.storepay.auth.dto.RefreshTokenRequest;
import com.emapaez.storepay.auth.jwt.IJwtService;
import com.emapaez.storepay.features.user.IUserService;
import com.emapaez.storepay.features.user.domain.dto.UserRequest;
import com.emapaez.storepay.features.user.domain.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final IUserService userService;
    private final IJwtService jwtService;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse authenticatedUser(@RequestBody AuthRequest request){
        UserDetails userDetails = authService.authenticate(request);
        String token = jwtService.generateToken(userDetails);
        String refresh = authService.authenticateRefreshToken((CredentialsEntity) userDetails);
        return new AuthResponse(token, refresh);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody @Valid UserRequest request){
        return userService.create(request);
    }


    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request){

        return authService.refreshAccessToken(request.refreshToken());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody RefreshTokenRequest request){
        authService.logoutRefresh(request.refreshToken());
    }
}
