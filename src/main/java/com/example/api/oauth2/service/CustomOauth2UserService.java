package com.example.api.oauth2.service;

import com.example.api.account.entity.UserRole;
import com.example.api.account.repository.AccountRepository;
import com.example.api.auth.entitiy.CustomUserDetails;
import com.example.api.domain.Account;
import com.example.api.oauth2.dto.OAuth2Response;
import com.example.api.oauth2.dto.Oauth2UserInfoRequest;
import com.example.api.oauth2.entity.handler.OAuth2ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Oauth2Service extends DefaultOAuth2UserService {
    private final AccountRepository accountRepository;
    private final List<OAuth2ResponseHandler> oauth2ResponseHandlers;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        log.info(String.valueOf(oAuth2User));

        String registrationId = request.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = createOAuth2Response(registrationId, oAuth2User);
        Oauth2UserInfoRequest userInfo = new Oauth2UserInfoRequest(oAuth2Response.getName(), oAuth2Response.getEmail(), UserRole.of(0));

        Account user = accountRepository.findByEmail(oAuth2Response.getEmail()).orElse(null);
        if(user == null) {
            return saveOauth2Account(userInfo);
        }
        return new CustomUserDetails(user.getAccountId(), user.getName(), user.getEmail(), user.getRoles());
    }

    private CustomUserDetails saveOauth2Account(final Oauth2UserInfoRequest userInfo) {
        Account account = new Account(
                userInfo.name(),
                userInfo.email(),
                Collections.singletonList(userInfo.role())
        );
        Account savedUser = accountRepository.save(account);
        return new CustomUserDetails(savedUser.getAccountId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRoles());
    }

    private OAuth2Response createOAuth2Response(final String registrationId, final OAuth2User oAuth2User) {
        for (OAuth2ResponseHandler oauth2ResponseHandler : oauth2ResponseHandlers) {
            if(oauth2ResponseHandler.supports(registrationId)) {
                return oauth2ResponseHandler.createResponse(oAuth2User.getAttributes());
            }
        }
        return null;
    }
}