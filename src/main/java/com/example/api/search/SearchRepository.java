package com.example.api.search;

import com.example.api.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchRepository extends JpaRepository<Account, Long> {

    @Query("SELECT DISTINCT a FROM Account a " +
            "JOIN PossibleBoard pb ON pb.employee = a " +
            "JOIN Category c ON c.account = a " +
            "WHERE (:category IS NULL OR c.categoryName = :category) " +
            "AND (:startTime IS NULL OR pb.startTime <= :startTime) " +
            "AND (:endTime IS NULL OR pb.endTime >= :endTime)")
    List<Account> searchAccountsByCategoryAndTime(
            @Param("category") String category,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}



