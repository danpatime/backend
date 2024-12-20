package com.example.api.auth.entitiy;

import com.example.api.account.repository.AccountRepository;
import com.example.api.domain.Account;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public CustomUserDetails loadUserByUserId(final Long userId) {
        Account user = accountRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NULL_USER));

        if(user.isDeleted())
            throw new BusinessException(ErrorCode.DELETED_USER);

        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .toList();

        return new CustomUserDetails(user.getAccountId(), authorities);
    }
}
