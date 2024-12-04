package com.example.api.account.domain;

public enum UserRole {
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

    public Integer getCode() {
        return code;
    }
}