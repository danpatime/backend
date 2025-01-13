package com.example.api.contracts.controller;

import com.example.api.contracts.ReviewService;
import com.example.api.contracts.dto.AddReviewCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<?> addReview(
            @RequestBody @Valid final AddReviewRequest request,
            @AuthenticationPrincipal final Long memberId
    ) {
        final AddReviewCommand command = request.toCommand(memberId);
        reviewService.saveReview(command);
        return ResponseEntity.ok(null);
    }

    record AddReviewRequest(
            @NotNull
            Long contractId,
            @Range(min = 0, max = 5)
            Integer reviewScore,
            String reviewContent
    ) {
        AddReviewCommand toCommand(final Long requestMemberId) {
            return new AddReviewCommand(requestMemberId, contractId, reviewContent, reviewScore);
        }
    }
}
