package com.example.api.account.entity;

import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class CodeGenerator {
    public static String generateCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(ErrorCode.FAIL_GENERATE_CODE);
        }
    }
}