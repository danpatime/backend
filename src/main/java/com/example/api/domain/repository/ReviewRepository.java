package com.example.api.domain.repository;

import com.example.api.domain.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.api.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.contract WHERE r.contract.offerEmployment.business.employer.accountId = :employerId")
    List<Review> loadReviewsByEmployerId(@Param("employerId") Long employerId);
}
=======
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    @Query("SELECT r FROM Review r WHERE (:reviewId IS NULL OR r.reviewId = :reviewId)")
    List<Review> findReviewsByDynamicQuery(@Param("reviewId") Long reviewId);
}
>>>>>>> ace3d7a (#69 test(ReviewServiceTest): 테스트 코드 추가)
