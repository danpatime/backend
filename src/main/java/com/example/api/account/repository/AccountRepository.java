package com.example.api.account.repository;

import com.example.api.domain.Account;
import com.example.api.setting.dto.EmailConsentResponse;
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

    @Modifying
    @Query("update Account a set a.profileImage = :profileImage where a.accountId = :accountId")
    void updateProfileImageByAccountId(@Param("profileImage") String profileImage, @Param("accountId") Long accountId);

    @Modifying
    @Query("update Account a " +
            "set a.starPoint = ((a.starPoint * a.workCount) + :starPoint) / (a.workCount+1), " +
            "a.workCount = a.workCount + 1 " +
            "where a.accountId in " +
            "(select oe.employee.accountId From OfferEmployment oe where oe.suggestId = :suggestId)")
    void updateWorkCountAndStarPointBySuggestId(@Param("suggestId") Long suggestId, @Param("starPoint") Integer newStarPoint);

    @Query("select a from Account a where a.accountId = :employeeId")
    Optional<Account> findByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT a.introduction FROM Account a WHERE a.accountId = :accountId")
    String findIntroductionByAccountId(Long accountId);

    @Query("select new com.example.api.setting.dto.EmailConsentResponse(a.emailReceivable) from Account a where a.accountId = :userId")
    Optional<EmailConsentResponse> findEmailReceivableById(@Param("userId") Long userId);

    Optional<Account> findByAccountId(Long accountId);
}