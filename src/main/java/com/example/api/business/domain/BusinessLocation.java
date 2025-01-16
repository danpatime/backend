package com.example.api.business.domain;

import com.example.api.account.entity.Location;
import lombok.Getter;

@Getter
public class BusinessLocation {
    private final Location location;

    public BusinessLocation(Location location) {
        this.location = location;
    }
}
