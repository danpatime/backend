package com.example.api.business.domain;

import lombok.Getter;

@Getter
public class BusinessRepresentationName {
    private final String representationName;

    public BusinessRepresentationName(String representationName) {
        this.representationName = representationName;
    }
}
