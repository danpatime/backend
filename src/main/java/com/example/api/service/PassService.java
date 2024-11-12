package com.example.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.HashMap;

@Service
public class PassService {

    @Value("${pass.api-key}")
    private String apiKey;

    @Value("S{pass.secret-key}")
    private String secretKey;

    @Value("${pass.endpoint}")
    private String endpoint;

    private final RestTemplate restTemplate;

    public PassService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendVerificationCode(String phoneNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-API-KEY", apiKey);
        headers.set("X-SECRET-KEY", secretKey);

        Map<String, String> body = new HashMap<>();
        body.put("phoneNumber", phoneNumber);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                requestEntity,
                String.class

        );

        return response.getBody();

    }

    public boolean verfyCode(String phoneNumber, String code) {
        String verificationEndpoint ="https://example.com/verifyCode";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type","application/json");
        headers.set("X-API-KEY",apiKey);
        headers.set("X-SECRET-KEY",secretKey);

        Map<String, String> body = new HashMap<>();
            body.put("phoneNumber",phoneNumber);
            body.put("code",code);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                verificationEndpoint,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody().contains("success");

    }

}
