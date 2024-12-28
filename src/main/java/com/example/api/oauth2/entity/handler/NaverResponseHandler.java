package com.example.api.oauth2.entity.handler;

import com.example.api.oauth2.dto.NaverResponse;
import com.example.api.oauth2.dto.OAuth2Response;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NaverResponseHandler implements OAuth2ResponseHandler {

    @Override
    public boolean supports(final String registrationId) {
        return "naver".equalsIgnoreCase(registrationId);
    }

    @Override
    public OAuth2Response createResponse(final Map<String, Object> attributes) {
        return new NaverResponse((Map<String, Object>) attributes.get("response"));
    }
}

