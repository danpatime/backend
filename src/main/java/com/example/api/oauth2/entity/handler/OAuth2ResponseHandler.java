package com.example.api.oauth2.entity.handler;

import com.example.api.oauth2.dto.OAuth2Response;

import java.util.Map;

public interface OAuth2ResponseHandler {
    boolean supports(final String registrationId);
    OAuth2Response createResponse(final Map<String, Object> attributes);
}