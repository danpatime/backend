package com.example.api.account.controller;

import com.example.api.account.controller.dto.request.EmailCodeRequestDto;
import com.example.api.account.controller.dto.request.EmailRequestDto;
import com.example.api.account.domain.Code;
import com.example.api.account.service.AccountService;
import com.example.api.account.controller.dto.request.SignUpRequestDto;
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
    public ResponseEntity<?> sendEmailCode(@Valid @RequestBody final EmailRequestDto request) {
        Code code = signUpService.sendEmail(request);
        String successMessage = signUpService.saveCode(code);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/email/verification")
    public ResponseEntity<?> verifyEmail(@Valid @RequestBody final EmailCodeRequestDto request) {
        String successMessage = signUpService.verifyEmail(request);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody final SignUpRequestDto request) {
        String successMessage = signUpService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }
}
