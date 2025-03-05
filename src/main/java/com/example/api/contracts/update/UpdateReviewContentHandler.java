package com.example.api.contracts.update;

import com.example.api.domain.Review;
import com.example.api.review.dto.ModifyReviewRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@Transactional(propagation = Propagation.MANDATORY)
class UpdateReviewContentHandler implements ReviewUpdateHandler {

    @Override
    public void update(Review review, ModifyReviewRequest command) {
        if (Objects.nonNull(command.reviewContent())) {
            review.setReviewContent(command.reviewContent());
        }
    }
}
