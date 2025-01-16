package com.example.api.review.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.api.review.ReviewService;
import com.example.api.review.dto.ReviewCommand;
import com.example.api.review.dto.ReviewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContractReviewServiceTest extends BaseIntegrationTest {
    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetReviews() {
        ReviewCommand reviewCommand = new ReviewCommand(1L);
        List<ReviewResponse> reviews = reviewService.getReviews(reviewCommand);
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).businessName()).isEqualTo("Tech Solutions Inc.");
        assertThat(reviews.get(0).reviewContent()).isEqualTo("Good work");
    }

    @Test
    void testGetReviewsWhenNoReviews() {
        ReviewCommand reviewCommand = new ReviewCommand(999L);
        List<ReviewResponse> reviews = reviewService.getReviews(reviewCommand);
        assertThat(reviews).isEmpty();
    }
}

