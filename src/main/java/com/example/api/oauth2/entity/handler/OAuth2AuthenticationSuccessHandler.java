package com.example.api.oauth2.entity.handler;

import com.example.api.account.entity.UserRole;
import com.example.api.account.repository.AccountRepository;
import com.example.api.auth.dto.AuthTokenRequest;
import com.example.api.auth.dto.UserDetailRequest;
import com.example.api.auth.entitiy.CustomUserDetails;
import com.example.api.auth.entitiy.RefreshToken;
import com.example.api.auth.repository.TokenRepository;
import com.example.api.auth.service.JwtTokenProvider;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.example.api.global.properties.JwtProperties;
import com.example.api.oauth2.entity.CookieUtils;
import com.example.api.oauth2.entity.HttpCookieOAuth2AuthorizationRequestRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.api.oauth2.entity.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider tokenProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;
    private final JwtProperties jwtProperties;

    @Value("app.oauth2. authorized-redirect-uris")
    List<String> authorizedRedirectUris;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        UserDetailRequest userDetailRequest = new UserDetailRequest(principal.getUserId(), (Collection<UserRole>) authentication.getAuthorities());
        setResponse(request, response, userDetailRequest);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private void setResponse(final HttpServletRequest request, final HttpServletResponse response, final UserDetailRequest userDetailRequest) {
        AuthTokenRequest authTokenRequest = generateAndSaveAuthToken(userDetailRequest);
        clearAuthenticationAttributes(request, response);

        Cookie accessTokenCookie = generateAccessCookie(authTokenRequest.accessToken());
        Cookie refreshTokenCookie = generateRefreshCookie(authTokenRequest.refreshToken());
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }

    @NotNull
    private Cookie generateAccessCookie(final String accessToken) {
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(jwtProperties.getAccessTokenValidTime().intValue());
        return accessTokenCookie;
    }

    @NotNull
    private Cookie generateRefreshCookie(final String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(jwtProperties.getRefreshTokenValidTime().intValue());
        // https 설정 후 secure 옵션 추가 필요
        return refreshTokenCookie;
    }
    
    @NotNull
    private AuthTokenRequest generateAndSaveAuthToken(final UserDetailRequest userDetailRequest) {
        Account user = accountRepository.findById(userDetailRequest.userId()).orElse(null);

        RefreshToken token = new RefreshToken(user);
        String accessToken = tokenProvider.generateAccessToken(userDetailRequest);
        String refreshToken = tokenProvider.generateRefreshToken(userDetailRequest, token.getId());

        token.putRefreshToken(refreshToken);
        tokenRepository.save(token);
        return new AuthTokenRequest(accessToken, refreshToken);
    }

    protected String determineTargetUrl(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BusinessException(ErrorCode.INVALID_REDIRECT_URI);
        }
        return redirectUri.orElse("http://localhost:3000"); // 로그인 성공 후 리다이렉트 url
    }

    private boolean isAuthorizedRedirectUri(final String uri) {
        URI clientRedirectUri = URI.create(uri);

        return authorizedRedirectUris
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(clientRedirectUri.equals(authorizedURI) || authorizedRedirectUri.equals("*")) {
                        return true;
                    }
                    return false;
                });
    }

    protected void clearAuthenticationAttributes(final HttpServletRequest request, final HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}