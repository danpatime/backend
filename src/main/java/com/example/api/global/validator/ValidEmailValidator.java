package com.example.api.global.validator;

import com.example.api.global.config.resolver.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_REGEX = "[a-zA-Z0-9._%+!#$&'*/=?^`{|}~]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 이메일이 null이거나, 공란인지 확인
        if (value == null || value.isBlank()) {
            return false;
        }

        // 기본 이메일 형식 확인
        if (!Pattern.matches(EMAIL_REGEX, value)) {
            return false;
        }

        // 도메인 유효성 확인 (간단한 예)
        try {
            String domain = value.substring(value.indexOf("@") + 1);
            java.net.InetAddress.getByName(domain);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
