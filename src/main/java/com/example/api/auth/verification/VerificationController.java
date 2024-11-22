package com.example.api.auth.verification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.api.auth.verification.dto.verifyRequest;
import com.example.api.auth.verification.dto.sendRequest;

@RestController
@RequestMapping("/api/v1/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;


    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody @Valid sendRequest request) {
        verificationService.sendVerificationCode(request.getPhoneNumber());
        return ResponseEntity.ok("인증 코드가 발송되었습니다.");
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody @Valid verifyRequest request) {
        boolean isVerified = verificationService.verifyCode(request.getPhoneNumber(), request.getEnteredCode());
        if (isVerified) {
            return ResponseEntity.ok("인증 성공");
        } else {
            return ResponseEntity.badRequest().body("인증 실패");
        }
    }
}