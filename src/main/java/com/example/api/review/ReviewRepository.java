package com.example.api.review;

import com.example.api.domain.Review;
<<<<<<< HEAD
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.contract WHERE r.contract.offerEmployment.business.employer.accountId = :employerId")
    List<Review> loadReviewsByEmployerId(@Param("employerId") Long employerId);

    @Query("SELECT r FROM Review r WHERE (:reviewId IS NULL OR r.reviewId = :reviewId)")
    List<Review> findReviewsByDynamicQuery(@Param("reviewId") Long reviewId);

=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByAccountId(final Long accountId);
    Page<Review> findReviewsByAccountId(final Long accountId, final Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.business b " +
            "JOIN FETCH r.account a " +
            "JOIN FETCH r.contract c " +
            "WHERE a.accountId = :accountId")
    List<Review> findReviewsByAccountIdWithDetails(final Long accountId);
>>>>>>> 0ff3ba1 (#53 feat(ReviewService): 서비스 코드 구현)
}


