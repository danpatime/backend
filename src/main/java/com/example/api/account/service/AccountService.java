package com.example.api.account.service;

import com.example.api.account.dto.EmailCodeRequest;
import com.example.api.account.dto.EmailRequest;
import com.example.api.account.dto.LoginIdRequest;
import com.example.api.account.entity.Code;
import com.example.api.account.entity.UserRole;
import com.example.api.account.repository.AccountRepository;
import com.example.api.account.dto.SignUpRequest;
import com.example.api.account.repository.CodeRepository;
import com.example.api.account.entity.MailSender;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    public Code sendEmail(@Validated final EmailRequest request) throws BusinessException {
        // 이미 가입된 이메일인지 검증
        validateDuplicateEmail(request);
        return mailSender.sendEmail(request);
    }

    @Transactional
    public String saveCode(@Validated final Code code){
        try {
            codeRepository.save(code);

            return "이메일 전송을 완료하였습니다.";
        } catch (Exception e){
            throw new BusinessException(ErrorCode.FAIL_SAVE_CODE);
        }
    }

    @Transactional
    public String verifyEmail(@Validated final EmailCodeRequest request) {
        Optional<Code> findCode = codeRepository.findFirstByEmailOrderByCreatedAtDesc(request.email());

        return findCode.map(code -> {
            if (code.getCode().equals(request.code())) {
                return "유효한 이메일입니다.";
            } else {
                throw new BusinessException(ErrorCode.INCORRECT_CODE);
            }
        }).orElseThrow(() -> new BusinessException(ErrorCode.EXPIRATION_DATE_END));
    }

    @Transactional
    public String signUp(@Validated final SignUpRequest request) {
        // 중복 로그인 ID 확인
        validateDuplicateLoginId(new LoginIdRequest(request.loginId()));
        // 계정 저장
        saveAccount(request);
        return "회원가입이 완료되었습니다";
    }

    @Transactional(readOnly = true)
    public Account loadAccount(final Long requestMemberId) {
        return accountRepository.findById(requestMemberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND_EXCEPTION));
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteAccount(final Long requestMemberId) {
        final Account account = accountRepository.findById(requestMemberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND_EXCEPTION));
        account.setDeleted(true);
    }

    private void saveAccount(final SignUpRequest request) {
        Collection<UserRole> roles = List.of(request.role());
        Account account = new Account(
                request.loginId(),
                passwordEncoder.encode(request.password()),
                request.name(),
                request.nickname(),
                request.email(),
                request.phoneNumber(),
                request.nationality(),
                roles,
                request.emailReceivable()
        );

        accountRepository.save(account);
    }

    private void validateDuplicateLoginId(final LoginIdRequest loginIdRequest) {
        if (accountRepository.existsByLoginId(loginIdRequest.loginId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
    }

    private void validateDuplicateEmail(final EmailRequest emailRequest) {
        if (accountRepository.existsByEmail(emailRequest.email())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}