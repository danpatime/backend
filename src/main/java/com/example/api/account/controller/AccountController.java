package com.example.api.account.controller;

import com.example.api.account.dto.EmailCodeRequest;
import com.example.api.account.dto.EmailRequest;
import com.example.api.account.entity.Code;
import com.example.api.account.service.AccountService;
import com.example.api.account.dto.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService signUpService;

    @PostMapping("/email/code")
    public ResponseEntity<String> sendEmailCode(@Valid @RequestBody final EmailRequest request) {
        Code code = signUpService.sendEmail(request);
        String successMessage = signUpService.saveCode(code);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/email/verification")
    public ResponseEntity<String> verifyEmail(@Valid @RequestBody final EmailCodeRequest request) {
        String successMessage = signUpService.verifyEmail(request);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody final SignUpRequest request) {
        String successMessage = signUpService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }
}