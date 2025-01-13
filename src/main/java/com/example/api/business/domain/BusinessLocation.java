package com.example.api.business.domain;

import lombok.Getter;

@Getter
public class BusinessLocation {
    private final String location;

    public BusinessLocation(String location) {
        this.location = location;
    }
}
