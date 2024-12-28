package com.example.api.oauth2.entity;

import com.example.api.oauth2.dto.KakaoResponse;
import com.example.api.oauth2.dto.OAuth2Response;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KakaoResponseHandler implements OAuth2ResponseHandler {

    @Override
    public boolean supports(String registrationId) {
        return "kakao".equalsIgnoreCase(registrationId);
    }

    @Override
    public OAuth2Response createResponse( Map<String, Object> attributes) {
        return new KakaoResponse(attributes);
    }
}

