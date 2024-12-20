package com.example.api.global.config.filter;

import com.example.api.auth.entitiy.JwtAuthenticationProvider;
import com.example.api.auth.entitiy.JwtAuthenticationToken;
import com.example.api.auth.service.JwtTokenProvider;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String accessToken = jwtTokenProvider.extractAccessToken(request);
            if (accessToken != null) {
                if (!jwtTokenProvider.isNotExpiredToken(accessToken)) {
                    throw new BusinessException(ErrorCode.EXPIRED_ACCESS_TOKEN.getErrorDescription(), ErrorCode.EXPIRED_ACCESS_TOKEN);
                }

                Authentication authentication = jwtAuthenticationProvider.authenticate(new JwtAuthenticationToken(accessToken));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (BusinessException ex) {
            response.setStatus(ex.getErrorCode().getHttpStatus().value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> errorResponse = Map.of(
                    "httpStatus", ex.getErrorCode().getHttpStatus().value(),
                    "errorCodeResponse", ex.getErrorCode().getErrorCode(),
                    "errorMessage", ex.getErrorCode().getErrorDescription()
            );

            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        } finally {
            SecurityContextHolder.clearContext();
        }
    }
}