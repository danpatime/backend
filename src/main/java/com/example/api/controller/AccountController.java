package com.example.api.controller;

import com.example.api.DTO.AccountDto;
import com.example.api.DTO.BusinessAccountDto;
import com.example.api.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> request) {
        String memberType = (String) request.get("memberType");

        try {
            if ("INDIVIDUAL".equalsIgnoreCase(memberType)) {
                AccountDto accountDto = new AccountDto();
                accountDto.setLoginId((String) request.get("loginId"));
                accountDto.setPassword((String) request.get("password"));
                accountDto.setName((String) request.get("name"));
                accountDto.setEmail((String) request.get("email"));
                accountDto.setPhone((String) request.get("phoneNumber"));

                accountService.signupIndividual(accountDto);
                return ResponseEntity.status(HttpStatus.CREATED).body("개인 회원가입 성공");

            } else if ("BUSINESS".equalsIgnoreCase(memberType)) {
                // 기업 회원 가입 요청 처리
                BusinessAccountDto businessAccountDto = new BusinessAccountDto();
                businessAccountDto.setLoginId((String) request.get("loginId"));
                businessAccountDto.setPassword((String) request.get("password"));
                businessAccountDto.setName((String) request.get("name"));
                businessAccountDto.setEmail((String) request.get("email"));
                businessAccountDto.setPhone((String) request.get("phoneNumber"));
                businessAccountDto.setRegistrationNumber((String) request.get("registrationNumber"));

                accountService.signupBusiness(businessAccountDto);
                return ResponseEntity.status(HttpStatus.CREATED).body("기업 회원가입 성공");

            } else {
                return ResponseEntity.badRequest().body("잘못된 회원 유형");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
