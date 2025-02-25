package com.example.api.review.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.board.dto.request.EmployeeIdRequest;
import com.example.api.review.dto.ReviewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContractReviewServiceTest {
    @Autowired
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetMyReviews() {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(1L);
        List<ReviewResponse> reviews = reviewService.getMyReviews(employeeIdRequest, new PageNumberRequest(1));
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).businessName()).isEqualTo("Tech Solutions Inc.");
        assertThat(reviews.get(0).reviewContent()).isEqualTo("Good work");
    }

    @Test
    void testGetReviewsWhenNoMyReviews() {
        EmployeeIdRequest employeeIdRequest = new EmployeeIdRequest(999L);
        List<ReviewResponse> reviews = reviewService.getMyReviews(employeeIdRequest, new PageNumberRequest(1));
        assertThat(reviews).isEmpty();
    }
}

