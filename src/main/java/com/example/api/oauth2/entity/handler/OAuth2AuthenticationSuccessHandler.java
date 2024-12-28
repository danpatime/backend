package com.example.api.oauth2.entity.handler;

import com.example.api.account.entity.UserRole;
import com.example.api.account.repository.AccountRepository;
import com.example.api.auth.dto.UserDetailRequest;
import com.example.api.auth.entitiy.CustomUserDetails;
import com.example.api.auth.entitiy.RefreshToken;
import com.example.api.auth.repository.TokenRepository;
import com.example.api.auth.service.JwtTokenProvider;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.example.api.oauth2.entity.CookieUtils;
import com.example.api.oauth2.entity.HttpCookieOAuth2AuthorizationRequestRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider tokenProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;

    @Value("app.oauth2. authorized-redirect-uris")
    List<String> authorizedRedirectUris;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        UserDetailRequest userDetailRequest = new UserDetailRequest(principal.getUserId(), (Collection<UserRole>) authentication.getAuthorities());
        Account user = accountRepository.findById(principal.getUserId()).orElse(null);
        RefreshToken token = new RefreshToken(user);
        String accessToken = tokenProvider.generateAccessToken(userDetailRequest);
        String refreshToken = tokenProvider.generateRefreshToken(userDetailRequest, token.getId() );
        token.putRefreshToken(refreshToken);
        tokenRepository.save(token);

        clearAuthenticationAttributes(request, response);

        response.setHeader("Authorization", "Bearer " + accessToken);

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        // secure 옵션 추가 필요
        response.addCookie(refreshTokenCookie);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BusinessException(ErrorCode.INVALID_REDIRECT_URI);
        }
        return redirectUri.orElse("http://localhost:3000");
    }

    private boolean isAuthorizedRedirectUri(String uri) {
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

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }
}
