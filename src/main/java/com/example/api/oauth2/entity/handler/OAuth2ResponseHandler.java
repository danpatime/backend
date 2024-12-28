package com.example.api.oauth2.entity.handler;

import com.example.api.oauth2.dto.OAuth2Response;

import java.util.Map;

public interface OAuth2ResponseHandler {
    public boolean supports(String registrationId);
    public OAuth2Response createResponse(Map<String, Object> attributes);
}
