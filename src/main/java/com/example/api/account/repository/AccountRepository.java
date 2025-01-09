package com.example.api.account.repository;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<Account> findByEmail(String email);

    Optional<Account> findUserByLoginId(String loginId);

    @Query("SELECT a.profileImage FROM Account a WHERE a.accountId = :accountId")
    Optional<String> findProfileImageByAccountId(@Param("accountId") Long accountId);

    @Query("update Account a set a.profileImage = :profileImage where a.accountId = :accountId")
    @Modifying
    void updateProfileImageByAccountId(@Param("profileImage") String profileImage, @Param("accountId") Long accountId);
}