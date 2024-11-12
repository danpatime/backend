package com.example.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PassServiceTest {

    @InjectMocks
    private PassService passService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void senVerificationCode_shouldReturnMockResponse() {
        String phoneNumber = "01012345678";
        String code ="123456";
        String mockResponse = "인증보내짐";

        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        boolean result = passService.verfyCode(phoneNumber, code);
        assertTrue(result);
    }
}
