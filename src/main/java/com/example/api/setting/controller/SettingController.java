package com.example.api.setting.controller;

import com.example.api.account.repository.AccountRepository;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.setting.dto.EmailConsentRequest;
import com.example.api.setting.dto.EmailConsentResponse;
import com.example.api.setting.service.SettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/setting")
@RequiredArgsConstructor
@Slf4j
public class SettingController {
    private final SettingService settingService;
    private final AccountRepository accountRepository;

    @PostMapping("/email-consent")
    public ResponseEntity<EmailConsentResponse> updateEmailConsent(
            @RequestParam("emailReceivable") final Boolean emailReceivable,
            @AuthenticationPrincipal final Long memberId
    ) {
        return ResponseEntity.ok(settingService.updateEmailConsent(new EmailConsentRequest(emailReceivable, memberId)));
    }

    @GetMapping("/email-consent")
    public ResponseEntity<EmailConsentResponse> getEmailConsent(
            @AuthenticationPrincipal final Long memberId
    ) {
        EmailConsentResponse emailConsentResponse = accountRepository.findEmailReceivableById(memberId).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        return ResponseEntity.ok(emailConsentResponse);
    }
}
