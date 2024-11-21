package com.example.api.auth.register;

import com.example.api.auth.AuthRepository;
import com.example.api.auth.register.dto.RegisterRequest;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class RegisterService {
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        // 중복 로그인 ID 확인
        validateDuplicateLoginId(request.getLoginId());

        // Account 엔티티 생성 (빌더 사용하지 않음)
        Account account = new Account();
        account.setLoginId(request.getLoginId());
        account.setPassword(passwordEncoder.encode(request.getPassword())); // 비밀번호 암호화
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        account.setSex(request.getSex());
        account.setAge(request.getAge());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setNickname(request.getNickname());
        account.setRegisteredDatetime(new Date()); // 현재 시간
        account.setStarPoint(0.0f); // 초기값 설정
        account.setWorkCount(0); // 초기값 설정
        account.setDeleted(false); // 초기값 설정

        // 엔티티 저장
        authRepository.save(account);
    }

    // 중복 로그인 ID 검증
    private void validateDuplicateLoginId(String loginId) {
        if (authRepository.existsByLoginId(loginId)) {
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
    }
}

