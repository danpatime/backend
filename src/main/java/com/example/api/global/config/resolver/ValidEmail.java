package com.example.api.global.config.resolver;

import com.example.api.global.validator.ValidEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidEmailValidator.class })
@Documented
public @interface ValidEmail {
    String message() default "이메일이 유효하지 않습니다.";
}
