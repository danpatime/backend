package com.example.api.controller;

import com.example.api.service.PassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class PassController {

    private final PassService passService;

    public  PassController(PassService passService) {
        this.passService = passService;
    }

    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestParam String phoneNumber) {
        String response = passService.sendVerificationCode(phoneNumber);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<Boolean> verifyCode(@RequestParam String phoneNumber, @RequestParam String code) {
        boolean isVerified = passService.verfyCode(phoneNumber, code);
        return  ResponseEntity.ok(isVerified);
    }
}
