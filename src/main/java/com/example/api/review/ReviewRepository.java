package com.example.api.review;

import com.example.api.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.contract c " +
            "WHERE c.offerEmployment.business.businessId = :employerId")
    List<Review> loadReviewsByEmployerId(@Param("employerId") Long employerId);

    @Query("SELECT r FROM Review r " +
            "WHERE (:reviewId IS NULL OR r.reviewId = :reviewId)")
    List<Review> findReviewsByDynamicQuery(@Param("reviewId") Long reviewId);

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.writer b " +
            "JOIN FETCH r.employee a " +
            "JOIN FETCH r.contract c " +
            "WHERE a.accountId = :accountId")
    List<Review> findReviewsByAccountIdWithDetails(@Param("accountId") Long accountId);

    List<Review> findReviewsByEmployee_AccountId(Long accountId);
}



