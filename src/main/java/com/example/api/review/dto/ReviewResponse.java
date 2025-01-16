package com.example.api.review.dto;

<<<<<<< HEAD
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.Review;

public record ReviewResponse(
        Long reviewId,
        Business writer,
        Account employee,
        int reviewStarPoint,
        String reviewContent

) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getReviewId(),
                review.getWriter(),
                review.getEmployee(),
                review.getReviewStarPoint(),
                review.getReviewContent()
        );
    }
}

=======
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReviewResponse(
        String businessName,
        Long businessId,
        LocalDateTime contractSrartTime,
        LocalDateTime contractEndTime,
        int reviewStarPoint,
        String reviewContent) {
}


>>>>>>> 0ff3ba1 (#53 feat(ReviewService): 서비스 코드 구현)
