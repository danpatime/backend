package com.example.api.board.dto.response;

import com.example.api.board.dto.request.FlavoredCategory;

import java.time.LocalDateTime;
import java.util.List;

public record PossibleDetailsResponse(
        String name,
        Integer age,
        String email,
        String phoneNumber,
        LocalDateTime recentlyUpdatedTime,
        LocalDateTime possibleStartTime,
        LocalDateTime possibleEndTime,
        List<FlavoredCategory> flavoredCategories,
        List<ExternalCareerResponse> externalExperience,
        List<InternalCareerResponse> internalExperience,
        Long contractCount,
        Integer starPoint
) {
}