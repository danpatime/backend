package com.example.api.auth.service;

import com.example.api.auth.dto.UserDetailRequest;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.example.api.global.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;

    // AccessToken 생성
    public String generateAccessToken(final UserDetailRequest user) {
        Claims claims = getClaimsFrom(user);
        return getTokenFrom(claims, jwtProperties.getAccessTokenValidTime() * 1000);
    }

    // AccessToken용 Claim 생성
    private Claims getClaimsFrom(final UserDetailRequest user) {
        Claims claims = Jwts.claims();
        claims.put("userId", user.userId());
        claims.put("auth", user.authorities());
        return claims;
    }

    // RefrshToken 생성
    public String generateRefreshToken(final UserDetailRequest user, final Long tokenId) {
        Claims claims = getClaimsFrom(user, tokenId);
        return getTokenFrom(claims, jwtProperties.getRefreshTokenValidTime() * 1000);
    }

    // RefreshToken용 Claim 생성
    private Claims getClaimsFrom(final UserDetailRequest user, final Long tokenId) {
        Claims claims = Jwts.claims();
        claims.put("userId", user.userId());
        claims.put("tokenId", tokenId);
        claims.put("auth", user.authorities());
        return claims;
    }

    // claim 정보로 Token 얻기
    private String getTokenFrom(final Claims claims, final long validTime) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validTime))
                .signWith(
                        Keys.hmacShaKeyFor(jwtProperties.getBytesSecretKey()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    // Token에서 유저 인증 정보 얻기
    public Long getUserIdFromToken(final String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getBytesSecretKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("userId", Long.class);
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
    }

    // AccessToken 값만 남도록 접두사 삭제
    public String extractAccessToken(final HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    //  만료된 토큰인지 확인
    public boolean isNotExpiredToken(final String token) {
        try {
            return !Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getBytesSecretKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    // 토큰으로부터 토큰 ID 얻기
    public Long getTokenIdFromToken(final String refreshToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getBytesSecretKey()))
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            return Long.parseLong(String.valueOf(claims.get("tokenId")));
        } catch (ExpiredJwtException e) {
            throw new BusinessException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
    }

    // 토큰으로부터 Claims 얻기
    public Claims getClaimsByToken(final String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getBytesSecretKey()))
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }
}