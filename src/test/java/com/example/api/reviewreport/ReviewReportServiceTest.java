package com.example.api.reviewreport;

import com.example.api.domain.Contract;
import com.example.api.domain.Review;
import com.example.api.domain.ReviewReport;
import com.example.api.reviewreport.dto.ReviewReportCommand;
import com.example.api.reviewreport.dto.ReviewReportResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReviewReportServiceTest {
    @Autowired
    private ReviewReportRepository reviewReportRepository;

    @Autowired
    private ReviewReportService reviewReportService;

    private Review review;
    private ReviewReportCommand reviewReportCommand;

    @BeforeEach
    void setUp() {
        Contract contract = new Contract(
                null,
                LocalDateTime.of(2025, 1, 1, 9, 0),
                LocalDateTime.of(2025, 1, 1, 18, 0),
                10000,
                true
        );
        review = new Review(5, "Excellent work!", contract);
        reviewReportCommand = new ReviewReportCommand(review, "Inappropriate content");
    }

    @Test
    @DisplayName("리뷰 신고가 성공적으로 처리되어야 한다")
    void shouldReportReviewSuccessfully() {
        when(reviewReportRepository.existsByReview(review)).thenReturn(false);
        when(reviewReportRepository.save(any(ReviewReport.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ReviewReportResponse response = reviewReportService.reportReview(reviewReportCommand);

        assertNotNull(response);
        assertEquals("리뷰 신고가 성공적으로 처리되었습니다.", response.message());

        verify(reviewReportRepository, times(1)).existsByReview(review);
        verify(reviewReportRepository, times(1)).save(any(ReviewReport.class));
    }

    @Test
    @DisplayName("이미 신고된 리뷰라면 예외를 던져야 한다")
    void shouldThrowExceptionIfReviewAlreadyReported() {
        when(reviewReportRepository.existsByReview(review)).thenReturn(true);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> reviewReportService.reportReview(reviewReportCommand)
        );
        assertEquals("이미 신고된 리뷰입니다.", exception.getMessage());

        verify(reviewReportRepository, times(1)).existsByReview(review);
        verify(reviewReportRepository, times(0)).save(any(ReviewReport.class));
    }
}

