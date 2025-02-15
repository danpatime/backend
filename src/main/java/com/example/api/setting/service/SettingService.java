package com.example.api.setting.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.domain.Account;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.setting.dto.EmailConsentRequest;
import com.example.api.setting.dto.EmailConsentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettingService {
    private final AccountRepository accountRepository;

    @Transactional
    public EmailConsentResponse updateEmailConsent(final EmailConsentRequest request) {
        Account user = accountRepository.findById(request.userId()).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
        user.setEmailReceivable(request.status());
        accountRepository.save(user);
        return new EmailConsentResponse(request.status());
    }
}
