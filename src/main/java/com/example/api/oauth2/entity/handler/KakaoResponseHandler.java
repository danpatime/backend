package com.example.api.oauth2.entity.handler;

import com.example.api.oauth2.dto.KakaoResponse;
import com.example.api.oauth2.dto.OAuth2Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.logging.Logger;

@Component
@Slf4j
public class KakaoResponseHandler implements OAuth2ResponseHandler {
    @Override
    public boolean supports(String registrationId) {
        return "kakao".equalsIgnoreCase(registrationId);
    }

    @Override
    public OAuth2Response createResponse( Map<String, Object> attributes) {
        log.info("Attributes: {}", attributes);
        return new KakaoResponse(attributes);
    }
}

