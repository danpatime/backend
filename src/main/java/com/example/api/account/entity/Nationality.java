package com.example.api.account.domain;

public enum Nationality {
    KOREAN(0, "내국인"),
    FOREIGN(1, "외국인");

    private final Integer code;
    private final String description;

    Nationality(final Integer code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static Nationality of(final Integer code) {
        for (Nationality nationality : values()) {
            if (nationality.getCode().equals(code)) {
                return nationality;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }
}
