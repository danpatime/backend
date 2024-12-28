package com.example.api.global.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class Oauth2Properties {
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakao_clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakao_redirectUri;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakao_clientSecret;
    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String kakao_authorizationGrantType;
    @Value("${spring.security.oauth2.client.registration.kakao.client-authentication-method}")
    private String kakao_clientAuthenticationMethod;
    @Value("#{'${spring.security.oauth2.client.registration.kakao.scope}'.split(',')}")
    List<String> kakao_scopes;
    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String kakao_authorizationUri;
    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String kakao_tokenUri;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakao_userInfoUri;
    @Value("${spring.security.oauth2.client.provider.kakao.user-name-attribute}")
    private String kakao_userNameAttribute;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naver_clientId;
    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String naver_redirectUri;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naver_clientSecret;
    @Value("${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
    private String naver_authorizationGrantType;
    @Value("${spring.security.oauth2.client.registration.naver.client-authentication-method}")
    private String naver_clientAuthenticationMethod;
    @Value("#{'${spring.security.oauth2.client.registration.naver.scope}'.split(',')}")
    List<String> naver_scopes;
    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String naver_authorizationUri;
    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String naver_tokenUri;
    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String naver_userInfoUri;
    @Value("${spring.security.oauth2.client.provider.naver.user-name-attribute}")
    private String naver_userNameAttribute;
}