package com.example.api.reviewreport;

import com.example.api.domain.Account;
import com.example.api.domain.Review;
import com.example.api.domain.ReviewReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewReportRepository extends JpaRepository<ReviewReport, Long> {
    boolean existsByReview(Review review);

    Optional<ReviewReport> findByReview(Review review);
}
