package com.example.api.contracts;

import com.example.api.contracts.dto.AddContractReviewCommand;
import com.example.api.domain.Review;
import org.springframework.stereotype.Service;

@Service
class ReviewMapper {
    public Review createReview(final AddContractReviewCommand reviewCommand) {
        return new Review(reviewCommand.starPoint(), reviewCommand.reviewContent());
    }
}
