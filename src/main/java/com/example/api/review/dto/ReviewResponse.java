package com.example.api.review.dto;

import com.example.api.domain.Account;
import com.example.api.domain.Business;

public record ReviewResponse(
        Long reviewId,
        Business writer,
        Account employee,
        int reviewStarPoint,
        String reviewContent

) { }
