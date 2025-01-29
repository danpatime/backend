package com.example.api.account.entity;

import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class CodeGenerator {
    @Value("${code.length}")
    private int length;
    @Value("${code.digit_range}")
    private int digitRange;

    public String generateCode() {
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(digitRange));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(ErrorCode.FAIL_GENERATE_CODE);
        }
    }
}