package com.example.api.oauth2.controller;

import com.example.api.auth.dto.LoginSuccessResponse;
import com.example.api.auth.service.AuthService;
import com.example.api.oauth2.dto.AccessTokenRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2/login")
@RequiredArgsConstructor
public class Oauth2Controller {
    private final AuthService authService;

    @GetMapping()
    public ResponseEntity login(@RequestParam(name ="token") String accessToken,
                                     HttpServletResponse response) {
        LoginSuccessResponse loginSuccessResponse = authService.oauth2Login(new AccessTokenRequest(accessToken));
        response.addCookie(loginSuccessResponse.refreshTokenCookie());
        return ResponseEntity.ok(loginSuccessResponse.responseBody());
    }
}
