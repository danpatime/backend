package com.example.api.global.config.filter;

import com.example.api.auth.entitiy.JwtAuthenticationProvider;
import com.example.api.auth.entitiy.JwtAuthenticationToken;
import com.example.api.auth.service.JwtTokenProvider;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
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

                log.info("authentication : {}", authentication);
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
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/v1/account/") || path.startsWith("/api/v1/auth/login");
    }
}
