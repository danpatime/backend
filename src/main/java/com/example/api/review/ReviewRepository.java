package com.example.api.review;

import com.example.api.domain.Review;
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

}
