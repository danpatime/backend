package com.example.api.review;

import com.example.api.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    @Query("SELECT r FROM Review r WHERE (:reviewId IS NULL OR r.reviewId = :reviewId)")
    List<Review> findReviewsByDynamicQuery(@Param("reviewId") Long reviewId);
}

