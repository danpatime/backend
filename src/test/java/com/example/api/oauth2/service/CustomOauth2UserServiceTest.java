package com.example.api.oauth2.service;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomOauth2UserServiceTest {
    @Autowired
    private CustomOauth2UserService customOauth2UserService;
    private WireMockServer kakaoWireMockServer;
    private WireMockServer naverWireMockServer;

    @BeforeEach
    void setupWireMock() {
        setKakaoWireMockServer();
        setNaverWireMockServer();
    }

    @AfterEach
    void teardownWireMock() {
        if (kakaoWireMockServer != null) {
            kakaoWireMockServer.stop();
        }

        if (naverWireMockServer != null) {
            naverWireMockServer.stop();
        }
    }

    @Test
    void loadUser_kakao() {
        ClientRegistration clientRegistration = setKakaoClientRegistration();
        OAuth2AccessToken token = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                "mock-access-token",
                null,
                null
        );
        OAuth2UserRequest mockRequest = new OAuth2UserRequest(clientRegistration, token);

        OAuth2User result = customOauth2UserService.loadUser(mockRequest);
        assertNotNull(result);
        assertEquals("danpat@kakao.com", result.getAttribute("email"));
        assertEquals("단팥", result.getAttribute("name"));
    }

    @Test
    void loadUser_naver() {
        ClientRegistration clientRegistration = setNaverClientRegistration();
        OAuth2AccessToken token = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                "mock-access-token",
                null,
                null
        );
        OAuth2UserRequest mockRequest = new OAuth2UserRequest(clientRegistration, token);

        OAuth2User result = customOauth2UserService.loadUser(mockRequest);
        assertNotNull(result);
        assertEquals("danpat@naver.com", result.getAttribute("email"));
        assertEquals("단팥", result.getAttribute("name"));
    }

    private void setNaverWireMockServer() {
        naverWireMockServer = new WireMockServer(8090); // 포트 설정
        naverWireMockServer.start();
        naverWireMockServer.stubFor(get(urlEqualTo("/v1/nid/me"))
                .withHeader("Authorization", matching("Bearer .*")) // Bearer 토큰 매칭
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody("""
                                {
                                  "resultcode": "00",
                                  "message": "success",
                                  "response": {
                                    "id": "1234567890",
                                    "email": "danpat@naver.com",
                                    "name": "단팥"
                                  }
                                }""")));
    }

    private void setKakaoWireMockServer() {
        kakaoWireMockServer = new WireMockServer(
                WireMockConfiguration.wireMockConfig().port(8089)
        );
        kakaoWireMockServer.start();
        kakaoWireMockServer.stubFor(get(urlEqualTo("/v2/user/me"))
                .withHeader("Authorization", matching("Bearer .*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "id": "0000000000",
                                  "properties": {
                                    "nickname": "단팥"
                                  },
                                  "kakao_account": {
                                    "email": "danpat@kakao.com"
                                  }
                                }""")));
    }

    @NotNull
    private ClientRegistration setKakaoClientRegistration() {
        return ClientRegistration.withRegistrationId("kakao")
                .clientId("mock-client-id")
                .clientSecret("mock-client-secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/kakao")
                .scope("profile", "email")
                .authorizationUri("http://localhost:8089/oauth/authorize")
                .tokenUri("http://localhost:8089/oauth/token")
                .userInfoUri("http://localhost:8089/v2/user/me")
                .userNameAttributeName("id")
                .clientName("Kakao Mock Client")
                .build();
    }

    @NotNull
    private ClientRegistration setNaverClientRegistration() {
        return ClientRegistration.withRegistrationId("naver")
                .clientId("mock-client-id")
                .clientSecret("mock-client-secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/naver")
                .scope("profile", "email")
                .authorizationUri("http://localhost:8090/oauth/authorize")
                .tokenUri("http://localhost:8090/oauth/token")
                .userInfoUri("http://localhost:8090/v1/nid/me")
                .userNameAttributeName("response")
                .clientName("Kakao Mock Client")
                .build();
    }
}
