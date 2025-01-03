package com.example.api.account.controller;

import com.example.api.account.dto.*;
import com.example.api.account.entity.Code;
import com.example.api.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService signUpService;
    private final AccountService accountService;

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

    @PostMapping("/sign-up/employee")
    public ResponseEntity<String> signUpEmployee(@Valid @RequestBody final SignUpEmployeeRequest request) {
        String successMessage = signUpService.signUpEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    @PostMapping("/sign-up/employer")
    public ResponseEntity<String> signUpEmployer(@Valid @RequestBody final SignUpEmployerRequest request) {
        String successMessage = signUpService.signUpEmployer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    /**
     * @param memberId
     * 현재, 로그인된 사용자에 대해서만 계정 삭제 요청이 가능하도록 구현
     * @return
     */
    @DeleteMapping("/my")
    public ResponseEntity<String> deleteAccount(
            @AuthenticationPrincipal final Long memberId
    ) {
        accountService.deleteAccount(memberId);
        return ResponseEntity.ok("delete account");
    }

    @PostMapping("/validation/business-number")
    public ResponseEntity<String> verifyBusinessNumber(@Valid @RequestBody final BusinessNumberRequest request) {
        String successMessage = signUpService.verifyBusinessNumber(request);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/sign-up/employer")
    public ResponseEntity<String> signUpEmployer(@Valid @RequestBody final SignUpEmployerRequest request) {
        String successMessage = signUpService.signUpEmployer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }
}