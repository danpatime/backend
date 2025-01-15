package com.example.api.account.entity;

import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    EMPLOYEE(0, "알바생"),
    EMPLOYER(1, "사장");

    private final Integer code;
    private final String description;

    UserRole(final Integer code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static UserRole of(final Integer code) {
        for (UserRole role : values()) {
            if (role.getCode().equals(code)) {
                return role;
            }
        }
        return null;
    }

    @JsonCreator
    public static UserRole from(String value) {
        for (UserRole role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new BusinessException(ErrorCode.INCORRECT_DATA);
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthority() {
        return this.name();
    }
}