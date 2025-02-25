package com.example.api.review.repository;

import com.example.api.review.dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCustomRepository {
    Page<ReviewResponse> findReviews(String nickname, Pageable pageable);
}
