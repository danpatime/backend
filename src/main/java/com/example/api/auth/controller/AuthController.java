package com.example.api.auth.controller;

import com.example.api.auth.dto.AuthTokenRequest;
import com.example.api.auth.dto.LoginRequest;
import com.example.api.auth.dto.LoginUserRequest;
import com.example.api.auth.dto.RefreshTokenRequest;
import com.example.api.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthTokenRequest> login(@Valid @RequestBody final LoginRequest loginRequest) {
        AuthTokenRequest authTokenRequest = authService.login(loginRequest);
        return ResponseEntity.ok(authTokenRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthTokenRequest> refresh(@Valid @RequestBody final RefreshTokenRequest refreshTokenRequest) {
        AuthTokenRequest authTokenRequest = authService.refreshAuthToken(refreshTokenRequest);
        return ResponseEntity.ok(authTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthTokenRequest> logout(@AuthenticationPrincipal final Object principal) {
        System.out.println("please = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Long userId = Long.parseLong(principal.toString());
        AuthTokenRequest authTokenRequest = authService.logout(new LoginUserRequest(userId));
        return ResponseEntity.ok(authTokenRequest);
    }
}