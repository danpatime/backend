package com.example.api.auth.controller;

import com.example.api.auth.dto.LoginRequest;
import com.example.api.auth.dto.LoginSuccessResponse;
import com.example.api.auth.dto.LoginUserRequest;
import com.example.api.auth.dto.RefreshTokenRequest;
import com.example.api.auth.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody final LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        LoginSuccessResponse loginSuccessResponse = authService.login(loginRequest);
        response.addCookie(loginSuccessResponse.refreshTokenCookie());
        return ResponseEntity.ok(loginSuccessResponse.responseBody());
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(
            @Valid @RequestBody final RefreshTokenRequest refreshTokenRequest,
            HttpServletResponse response) {
        LoginSuccessResponse loginSuccessResponse = authService.refreshAuthToken(refreshTokenRequest);
        response.addCookie(loginSuccessResponse.refreshTokenCookie());
        return ResponseEntity.ok(loginSuccessResponse.responseBody());
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@AuthenticationPrincipal final Long userId) {
        LoginSuccessResponse loginSuccessResponse = authService.logout(new LoginUserRequest(userId));
        return ResponseEntity.ok(loginSuccessResponse.responseBody());
    }
}