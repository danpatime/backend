package com.example.api.contracts.controller;

import com.example.api.contracts.ReviewQueryService;
import com.example.api.contracts.ContractReviewService;
import com.example.api.contracts.dto.AddReviewCommand;
import com.example.api.contracts.dto.QueryEmployersReviewCommand;
import com.example.api.contracts.dto.ReviewResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contracts")
public class ContractReviewController {
    private final ContractReviewService contractReviewService;
    private final ReviewQueryService reviewQueryService;


    /**
     * @param requestMemberId 고용자 ( employer ID ) 가 본인이 작성한 리뷰 목록 가져오기
     * @return
     */
    @GetMapping("/review/my")
    public ResponseEntity<List<ReviewResponse>> getMyReview(
            @AuthenticationPrincipal final Long requestMemberId
    ) {
        final QueryEmployersReviewCommand command = new QueryEmployersReviewCommand(requestMemberId);
        return ResponseEntity.ok(reviewQueryService.loadReviewsWithEmployerId(command));
    }

    @PostMapping("/review")
    public ResponseEntity<?> addReview(
            @RequestBody @Valid final AddReviewRequest request,
            @AuthenticationPrincipal final Long memberId
    ) {
        final AddReviewCommand command = request.toCommand(memberId);
        contractReviewService.saveReview(command);
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
