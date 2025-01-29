package com.example.api.account.service;

import com.example.api.account.dto.BusinessNumberRequest;
import com.example.api.account.dto.SignUpEmployerRequest;
import com.example.api.account.repository.AccountRepository;
import com.example.api.global.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("중복된 ID로 회원가입 시 예외가 발생해야 한다")
    void shouldThrowExceptionWhenUserIdAlreadyExists() {
        String userId = "existingUser";
        SignUpEmployerRequest mockRequest = mock(SignUpEmployerRequest.class);
        when(mockRequest.loginId()).thenReturn(userId);
        when(accountRepository.existsByLoginId(userId)).thenReturn(true);

        BusinessException thrown = assertThrows(BusinessException.class,
                () -> accountService.signUpEmployer(mockRequest));
        assertEquals("중복된 ID입니다.", thrown.getErrorCode().getErrorDescription());
    }

    @Test
    @DisplayName("유효하지 않은 사업자 번호로 검증 요청 시 예외가 발생해야 한다")
    void shouldThrowExceptionWhenBusinessNumberIsInvalid() {
        BusinessNumberRequest businessNumberRequest = new BusinessNumberRequest(
                "1041736263 가짜지롱",
                "20231123",
                "김태영",
                "김태영닷컴");

        BusinessException thrown = assertThrows(BusinessException.class,
                () -> accountService.verifyBusinessNumber(businessNumberRequest));
        assertEquals("사업자 등록 정보를 확인할 수 없습니다.", thrown.getErrorCode().getErrorDescription());
    }

    @Test
    @DisplayName("유효한 사업자 번호로 검증 요청 시 성공해야 한다.")
    void shouldReturnSuccessWhenValidBusinessNumberIsProvided() {
        BusinessNumberRequest businessNumberRequest = new BusinessNumberRequest(
                "1041736263",
                "김태영닷컴",
                "김태영",
                "20231123");

        String isValid = accountService.verifyBusinessNumber(businessNumberRequest);
        assertEquals("유효한 사업자 등록 정보입니다.", isValid);
    }
}
