package com.example.api.auth.controller;

import com.example.api.auth.dto.AuthTokenRequest;
import com.example.api.auth.dto.LoginRequest;
import com.example.api.auth.dto.LoginUserRequest;
import com.example.api.auth.dto.RefreshTokenRequest;
import com.example.api.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginSuccessResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        LoginSuccessResponse loginSuccessResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginSuccessResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginSuccessResponse> refresh(@Valid @RequestBody final RefreshTokenRequest refreshTokenRequest) {
        LoginSuccessResponse loginSuccessResponse = authService.refreshAuthToken(refreshTokenRequest);
        return ResponseEntity.ok(loginSuccessResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginSuccessResponse> logout(@AuthenticationPrincipal final Object principal) {
        Long userId = Long.parseLong(principal.toString());
        LoginSuccessResponse loginSuccessResponse = authService.logout(new LoginUserRequest(userId));
        return ResponseEntity.ok(loginSuccessResponse);
    }
}