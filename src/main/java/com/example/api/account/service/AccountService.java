package com.example.api.account.service;

import com.example.api.account.dto.*;
import com.example.api.account.entity.Code;
import com.example.api.business.domain.BusinessLocation;
import com.example.api.account.entity.UserRole;
import com.example.api.account.repository.AccountRepository;
import com.example.api.account.repository.CodeRepository;
import com.example.api.account.entity.MailSender;
import com.example.api.account.repository.LocationRepository;
import com.example.api.business.BusinessRepository;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.global.properties.VendorProperties;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final CodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;
    private final BusinessRepository businessRepository;
    private final RestTemplate restTemplate;
    private final VendorProperties vendorProperties;
    private final LocationRepository locationRepository;

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
    public String signUpEmployee(@Validated final SignUpEmployeeRequest request) {
        // 중복 로그인 ID 확인
        validateDuplicateLoginId(new LoginIdRequest(request.loginId()));
        // 계정 저장
        saveEmployeeAccount(request);
        return "회원가입이 완료되었습니다";
    }

    @Transactional
    public String signUpEmployer(@Valid final SignUpEmployerRequest request) {
        // 중복 로그인 ID 확인 (사장)
        validateDuplicateLoginId(new LoginIdRequest(request.loginId()));
        // 계정 저장
        saveEmployerAccount(request);
        return "회원가입이 완료되었습니다";
    }

    @Transactional(readOnly = true)
    public Account loadAccount(final Long requestMemberId) {
        return accountRepository.findById(requestMemberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND_EXCEPTION));
    }

    @Transactional
    public void deleteAccount(final Long requestMemberId) {
        final Account account = accountRepository.findById(requestMemberId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND_EXCEPTION));
        account.setDeleted(true);
    }
    private Account saveEmployeeAccount(final SignUpEmployeeRequest request) {
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
        return accountRepository.save(account);
    }

    private void saveEmployerAccount(final SignUpEmployerRequest request) {
        Collection<UserRole> roles = List.of(request.role());
        Account account = new Account(
                request.loginId(),
                passwordEncoder.encode(request.password()),
                request.email(),
                request.phoneNumber(),
                request.nationality(),
                roles
        );
        Account savedUser = accountRepository.save(account);

        BusinessLocation savedLocation = locationRepository.save(request.location());
        Business business = new Business(
                savedUser,
                request.businessRegistrationNumber(),
                request.businessName(),
                request.representationName(),
                request.businessOpenDate(),
                savedLocation
        );
        businessRepository.save(business);
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

    @Transactional
    public String verifyBusinessNumber(@Validated final BusinessNumberRequest request){
        URI uri = createUrl();
        HttpEntity<Map<String, Object>> requestEntity = getBusinessValidateApiRequestEntity(request);

        ResponseEntity<BusinessNumberResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                BusinessNumberResponse.class
        );

        log.info("response.getBody()={}", response.getBody());

        String valid = Optional.ofNullable(response.getBody())
                .map(BusinessNumberResponse::getValid)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_BUSINESS_NUMBER));

        if (!"01".equals(valid)) {
            throw new BusinessException(ErrorCode.INVALID_BUSINESS_NUMBER);
        }

        return "유효한 사업자 등록 정보입니다.";
    }

    @NotNull
    private URI createUrl() {
        try {
            String encodedServiceKey = URLEncoder.encode(vendorProperties.getServiceKey(), StandardCharsets.UTF_8);
            String url = vendorProperties.getBaseUrl() + "?serviceKey=" + encodedServiceKey;
            return new URI(url);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 프론트 서버 배포 후 수정, 회원가입 end point로 변경
            return URI.create("http://localhost:3000/");
        }
    }

    @NotNull
    private HttpEntity<Map<String, Object>> getBusinessValidateApiRequestEntity(BusinessNumberRequest request) {
        Map<String, String> business = new HashMap<>();
        business.put("b_no", request.businessRegistrationNumber());
        business.put("start_dt", request.businessOpenDate());
        business.put("p_nm", request.representationName());
        business.put("b_nm", request.businessName());
        return new HttpEntity<>(Collections.singletonMap("businesses", Collections.singletonList(business)));
    }
}