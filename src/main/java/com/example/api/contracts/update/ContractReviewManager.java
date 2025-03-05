package com.example.api.contracts.update;

import com.example.api.domain.Review;
import com.example.api.review.dto.ModifyReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractReviewManager {
    private final List<ReviewUpdateHandler> updateHandlers;

    public void update(final Review review, final ModifyReviewRequest command) {
        updateHandlers.stream()
                .forEach(reviewUpdateHandler -> reviewUpdateHandler.update(review, command));
    }
}
