package com.example.api.global.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VendorProperties {
    @Value("${vendor.api.base-url}")
    private String baseUrl;
    @Value("${vendor.api.service-key}")
    private String serviceKey;
}