package com.example.api.review.repository;

import com.example.api.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {

    @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.writer b " +
            "JOIN FETCH r.employee a " +
            "JOIN FETCH r.contract c " +
            "WHERE c.offerEmployment.business.businessId = :employerId")
    Page<Review> loadReviewsByEmployerId(@Param("employerId") Long employerId, Pageable pageable);

    Page<Review> findAllByEmployee_AccountId(Long accountId, Pageable pageable);

    @Query("select r.reviewStarPoint from Review r where r.reviewId = :suggestId")
    Integer findReviewStarPointBySuggestId(@Param("suggestId") Long suggestId);
}