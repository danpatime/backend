package com.example.api.account.service;

import com.example.api.account.controller.dto.request.EmailCodeRequestDto;
import com.example.api.account.controller.dto.request.EmailRequestDto;
import com.example.api.account.controller.dto.request.LoginIdRequestDto;
import com.example.api.account.domain.Code;
import com.example.api.account.repository.AccountRepository;
import com.example.api.account.controller.dto.request.SignUpRequestDto;
import com.example.api.account.repository.CodeRepository;
import com.example.api.account.domain.MailSender;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository authRepository;
    private final CodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    public Code sendEmail(final EmailRequestDto request) throws BusinessException {
        // 이미 가입된 이메일인지 검증
        validateDuplicateEmail(request);
        return mailSender.sendEmail(request);
    }

    @Transactional
    public String saveCode(final Code code){
        try {
            codeRepository.save(code);
            return "이메일 전송을 완료하였습니다.";
        } catch (Exception e){
            throw new BusinessException(ErrorCode.FAIL_SAVE_CODE);
        }
    }

    @Transactional
    public String verifyEmail(final EmailCodeRequestDto request) {
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
    public String signUp(final SignUpRequestDto request) {
        // 중복 로그인 ID 확인
        validateDuplicateLoginId(new LoginIdRequestDto(request.loginId()));
        // 계정 저장
        saveAccount(request);
        return "회원가입이 완료되었습니다";
    }

    private void saveAccount(final SignUpRequestDto request) {
        Account account = new Account(
                request.loginId(),
                passwordEncoder.encode(request.password()),
                request.name(),
                request.nickname(),
                request.email(),
                request.phoneNumber(),
                request.nationality(),
                request.role()
        );

        authRepository.save(account);
    }

    private void validateDuplicateLoginId(final LoginIdRequestDto loginIdRequest) {
        if (authRepository.existsByLoginId(loginIdRequest.loginId())) {
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
    }

    private void validateDuplicateEmail(final EmailRequestDto emailRequest) {
        if (authRepository.existsByEmail(emailRequest.email())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
