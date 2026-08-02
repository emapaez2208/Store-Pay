package com.emapaez.storepay.auth;

import com.emapaez.storepay.auth.dto.AuthRequest;
import com.emapaez.storepay.auth.dto.AuthResponse;
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
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody @Valid UserRequest request){
        return userService.create(request);
    }
}
