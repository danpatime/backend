package com.example.api.reviewreport;

import com.example.api.domain.Account;
import com.example.api.domain.Review;
import com.example.api.domain.ReviewReport;
import com.example.api.reviewreport.dto.ReviewReportCommand;
import com.example.api.reviewreport.dto.ReviewReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewReportService {
    private final ReviewReportRepository reviewReportRepository;

    @Transactional
    public ReviewReportResponse reportReview(final ReviewReportCommand reviewReportCommand) {
        validateAlreadyReported(reviewReportCommand.reviewId());
        final ReviewReport savedReport = saveReviewReport(reviewReportCommand);
        return createResponse(savedReport);
    }

    private void validateAlreadyReported(final Review review) {
        boolean alreadyReported = reviewReportRepository.existsByReview(review);
        if (alreadyReported) {
            throw new IllegalStateException("이미 신고된 리뷰입니다.");
        }
    }

    private ReviewReport saveReviewReport(final ReviewReportCommand command) {
        return reviewReportRepository.save(command.toEntity());
    }

    private ReviewReportResponse createResponse(ReviewReport savedReport) {
        return new ReviewReportResponse(
                savedReport.getReportId(),
                "리뷰 신고가 성공적으로 처리되었습니다."
        );
    }
}