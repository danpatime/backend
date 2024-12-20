package com.example.api.auth.service;

import com.example.api.auth.entitiy.RefreshToken;
import com.example.api.auth.dto.*;
import com.example.api.auth.repository.AuthRepository;
import com.example.api.auth.repository.TokenRepository;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    @Transactional
    public AuthTokenRequest login(@Validated final LoginRequest request) {
        final Account user = getUserByLoginId(request.loginId());
        checkPassword(request, user);
        return generateAuthToken(user);
    }

    private Account getUserByLoginId(final String loginId) {
        final Account user = authRepository.findUserByLoginId(loginId)
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

    private AuthTokenRequest generateAuthToken(final Account user) {
        String accessToken = jwtTokenProvider.generateAccessToken(new UserDetailRequest(user.getAccountId(), user.getRoles()));
        String refreshToken = generateRefreshToken(user);

        return new AuthTokenRequest(accessToken,refreshToken);
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
    public AuthTokenRequest refreshAuthToken(@Validated final RefreshTokenRequest request){
        if(!jwtTokenProvider.isNotExpiredToken(request.refreshToken())){
            throw new BusinessException(ErrorCode.EXPIRED_REFRESH_TOKEN);
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(request.refreshToken());
        Account user = getUserById(userId);
        return generateAuthToken(user);
    }

    @Transactional
    public AuthTokenRequest logout(@Validated final LoginUserRequest loginUserRequest) {
        Account user = getUserById(loginUserRequest.userId());
        tokenRepository.deleteAllByUser(user);
        return new AuthTokenRequest(null, null);
    }

    private Account getUserById(final Long userId) {
        return authRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));
    }
}