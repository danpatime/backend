package com.example.api.contracts.update;


import com.example.api.domain.Review;
import com.example.api.review.dto.ModifyReviewRequest;

public interface ReviewUpdateHandler {
    void update(final Review review, final ModifyReviewRequest command);
}
