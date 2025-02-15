package com.example.api.auth.service;

import com.example.api.account.repository.AccountRepository;
import com.example.api.auth.entitiy.RefreshToken;
import com.example.api.auth.dto.*;
import com.example.api.auth.repository.TokenRepository;
import com.example.api.aws.dto.OldKeyRequest;
import com.example.api.aws.service.S3Service;
import com.example.api.domain.Account;
import com.example.api.global.exception.BusinessException;
import com.example.api.global.exception.ErrorCode;
import com.example.api.global.properties.JwtProperties;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;
    private final JwtProperties jwtProperties;
    private final S3Service s3Service;

    @Transactional
    public LoginSuccessResponse login(@Validated final LoginRequest request) {
        final Account user = getUserByLoginId(request.loginId());
        checkPassword(request, user);
        return generateAuthToken(user);
    }

    private Account getUserByLoginId(final String loginId) {
        final Account user = accountRepository.findUserByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));

        if(user.isDeleted())
            throw new BusinessException(ErrorCode.DELETED_USER);

        return user;
    }

    private void checkPassword(final LoginRequest request, final Account user) {
        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INCORRECT_PASSWORD);
        }
    }

    private LoginSuccessResponse generateAuthToken(final Account user) {
        String accessToken = jwtTokenProvider.generateAccessToken(new UserDetailRequest(user.getAccountId(), user.getRoles()));
        String refreshToken = generateRefreshToken(user);
        Cookie refreshTokenCookie = genreateRefreshTokenCookie(refreshToken);

        String role = user.getRoles().stream().findFirst().get().getAuthority();    // 회원가입 시에 무조건 역할이 들어가기에 바로 get으로 꺼냄
        String profile = s3Service.getImage(new OldKeyRequest(user.getProfileImage()));
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", accessToken);
        responseBody.put("userId", String.valueOf(user.getAccountId()));
        responseBody.put("userRole", role);
        responseBody.put("name", user.getName());
        responseBody.put("profile", profile);
        return new LoginSuccessResponse(refreshTokenCookie, responseBody);
    }

    @NotNull
    private Cookie genreateRefreshTokenCookie(String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(Math.toIntExact(jwtProperties.getRefreshTokenValidTime()));
        return refreshTokenCookie;
    }

    private String generateRefreshToken(final Account user) {
        RefreshToken token = new RefreshToken(user);
        if(tokenRepository.findByUser(user).isPresent()) {
            tokenRepository.deleteAllByUser(user);
        }

        tokenRepository.save(token);
        String refreshToken = jwtTokenProvider.generateRefreshToken(new UserDetailRequest(user.getAccountId(), user.getRoles()), token.getId());

        token.putRefreshToken(refreshToken);
        return refreshToken;
    }

    @Transactional
    public LoginSuccessResponse refreshAuthToken(@Validated final RefreshTokenRequest request){
        if(!jwtTokenProvider.isNotExpiredToken(request.refreshToken())){
            throw new BusinessException(ErrorCode.EXPIRED_REFRESH_TOKEN);
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(request.refreshToken());
        Account user = getUserById(userId);
        return generateAuthToken(user);
    }

    @Transactional
    public LoginSuccessResponse logout(@Validated final LoginUserRequest loginUserRequest) {
        Account user = getUserById(loginUserRequest.userId());
        tokenRepository.deleteAllByUser(user);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("accessToken", null);
        responseBody.put("userId", null);
        responseBody.put("userRole", null);
        return new LoginSuccessResponse(null, responseBody);
    }

    private Account getUserById(final Long userId) {
        return accountRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
    }
}