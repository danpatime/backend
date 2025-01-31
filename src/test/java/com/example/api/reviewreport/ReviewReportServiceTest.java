//package com.example.api.reviewreport;
//
//import com.example.api.domain.Review;
//import com.example.api.review.ReviewRepository;
//import com.example.api.reviewreport.dto.ReviewReportCommand;
//import com.example.api.reviewreport.dto.ReviewReportResponse;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@SpringBootTest
//class ReviewReportServiceTest {
//    @Autowired
//    private ReviewReportService reviewReportService;
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private ReviewReportRepository reviewReportRepository;
//
//    private Review review;
//
//    @BeforeEach
//    void setUp() {
//        reviewRepository.deleteAll();
//        reviewReportRepository.deleteAll();
//
//        review = new Review();
//        review.setReviewStarPoint(4);
//        review.setReviewContent("리뷰 내용");
//        review = reviewRepository.save(review);
//    }
//
//    @Test
//    @Order(1)
//    @DisplayName("정상적으로 리뷰 신고 성공")
//    void reportReview_ShouldReportReviewSuccessfully() {
//        ReviewReportCommand command = new ReviewReportCommand(
//                review,
//                "부적절한 리뷰"
//        );
//        ReviewReportResponse response = reviewReportService.reportReview(command);
//        assertNotNull(response);
//        assertEquals("리뷰 신고가 성공적으로 처리되었습니다.", response.message());
//        assertNotNull(reviewReportRepository.findByReview(review).orElse(null));
//    }
//
//    @Test
//    @Order(2)
//    @DisplayName("중복 신고 시 예외 발생")
//    void reportReview_ShouldThrowExceptionWhenAlreadyReported() {
//        ReviewReportCommand command = new ReviewReportCommand(
//                review,
//                "부적절한 리뷰"
//        );
//        reviewReportService.reportReview(command);
//        assertThrows(IllegalStateException.class,
//                () -> reviewReportService.reportReview(command));
//    }
//
//    @Test
//    @Order(3)
//    @DisplayName("존재하지 않는 리뷰 신고 시 예외 발생")
//    void reportReview_ShouldThrowExceptionWhenReviewNotFound() {
//        Review nonExistentReview = new Review();
//        nonExistentReview.setReviewId(999L);
//        ReviewReportCommand command = new ReviewReportCommand(
//                nonExistentReview,
//                "부적절한 리뷰"
//        );
//        assertThrows(IllegalArgumentException.class,
//                () -> reviewReportService.reportReview(command));
//    }
//}
//
