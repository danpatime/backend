package com.example.api.review.controller;

import com.example.api.review.ReviewService;
import com.example.api.review.dto.ReviewCommand;
import com.example.api.review.dto.ReviewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ContractReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private ReviewResponse reviewResponse1;
    private ReviewResponse reviewResponse2;

    @BeforeEach
    void setUp() {
        reviewResponse1 = new ReviewResponse(
                "Business A", 101L, LocalDateTime.now(), LocalDateTime.now(), 5, "Great service!");
        reviewResponse2 = new ReviewResponse(
                "Business B", 102L, LocalDateTime.now(), LocalDateTime.now(), 4, "Good experience.");
    }

    @Test
    void testGetMyReviews() throws Exception {
        when(reviewService.getReviews(any(ReviewCommand.class)))
                .thenReturn(List.of(reviewResponse1, reviewResponse2));
        mockMvc.perform(get("/api/v1/info/my/reviews")
                        .param("accountId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviews").isArray())
                .andExpect(jsonPath("$.reviews[0].businessName").value("Business A"))
                .andExpect(jsonPath("$.reviews[1].reviewContent").value("Good experience."));
    }

    @Test
    void testGetMyReviewsNoData() throws Exception {
        when(reviewService.getReviews(any(ReviewCommand.class)))
                .thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/v1/info/my/reviews")
                        .param("accountId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reviews").isEmpty());
    }
}
