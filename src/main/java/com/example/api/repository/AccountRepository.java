package com.example.api.repository;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByLoginId(String loginId);


}
