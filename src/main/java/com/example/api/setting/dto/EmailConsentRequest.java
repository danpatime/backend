package com.example.api.setting.dto;

public record EmailConsentRequest(
        Boolean status,
        Long userId
) {
}
