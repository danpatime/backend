package com.example.api.oauth2.dto;

import java.util.Map;

public record KakaoResponse(Map<String, Object> attributes) implements OAuth2Response {

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return kakaoAccount != null ? kakaoAccount.get("email").toString() : null;
    }

    @Override
    public String getName() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("properties");
        return kakaoAccount != null ? kakaoAccount.get("nickname").toString() : null;
    }
}
