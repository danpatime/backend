package com.example.api.review;

import com.example.api.domain.Contract;
import com.example.api.domain.Review;
import com.example.api.review.dto.ReviewCommand;
import com.example.api.review.dto.ReviewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReviewServiceTest {

    @MockBean
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    private ReviewCommand reviewCommand;
    private List<Review> reviews;
    private Review review;

    @BeforeEach
    public void setUp() {
        reviewCommand = new ReviewCommand(1L);
        Contract someContract = new Contract();
        review = new Review(
                5,
                "Great job!",
                someContract
        );
        reviews = List.of(review);
    }

    @Test
    @DisplayName("모든 리뷰를 조회할 수 있어야 한다")
    void shouldGetAllReviews() {
        when(reviewRepository.findReviewsByDynamicQuery(null)).thenReturn(reviews);

        List<ReviewResponse> response = reviewService.getAllReviews();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Great job!", response.get(0).reviewContent());

        verify(reviewRepository, times(1)).findReviewsByDynamicQuery(null);
    }

    @Test
    @DisplayName("특정 리뷰 ID로 리뷰를 조회할 수 있어야 한다")
    void shouldGetReviewsByEmployee() {
        when(reviewRepository.findReviewsByDynamicQuery(1L)).thenReturn(reviews);

        List<ReviewResponse> response = reviewService.getReviewsByEmployee(1L);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Great job!", response.get(0).reviewContent());

        verify(reviewRepository, times(1)).findReviewsByDynamicQuery(1L);
    }

    @Test
    @DisplayName("특정 사용자의 모든 리뷰를 조회할 수 있어야 한다")
    void shouldGetReviewsByAccountId() {
        when(reviewRepository.findReviewsByEmployee_AccountId(reviewCommand.accountId())).thenReturn(reviews);

        List<ReviewResponse> response = reviewService.getReviews(reviewCommand);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Great job!", response.get(0).reviewContent());

        verify(reviewRepository, times(1)).findReviewsByEmployee_AccountId(reviewCommand.accountId());
    }

    @Test
    @DisplayName("특정 사용자의 상세 리뷰를 조회할 수 있어야 한다")
    void shouldGetReviewsByEmployeeWithDetails() {
        when(reviewRepository.findReviewsByAccountIdWithDetails(reviewCommand.accountId())).thenReturn(reviews);

        List<ReviewResponse> response = reviewService.getReviewsByEmployeeWithDetails(reviewCommand);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Great job!", response.get(0).reviewContent());

        verify(reviewRepository, times(1)).findReviewsByAccountIdWithDetails(reviewCommand.accountId());
    }
}



