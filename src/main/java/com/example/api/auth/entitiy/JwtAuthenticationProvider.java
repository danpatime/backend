package com.example.api.auth.entitiy;

import com.example.api.auth.service.JwtTokenProvider;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService customUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof JwtAuthenticationToken)) {
            return null;
        }
        String accessToken = authentication.getCredentials().toString();

        Claims claims = jwtTokenProvider.getClaimsByToken(accessToken);
        if (claims.get("auth") == null) {
            throw new BusinessException(ErrorCode.TOKEN_MISSING_AUTHORITY);
        }

        Long userId = claims.get("userId", Long.class);

        CustomUserDetails customUserDetails = customUserDetailService.loadUserByUserId(userId);
        return new JwtAuthenticationToken(userId, accessToken, customUserDetails.getAuthorities());
    }

    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
