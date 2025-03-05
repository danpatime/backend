package com.example.api.search.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record SearchCommand(
        Long employeeId,
        String sido,
        String sigugun,
        String dong,
        Long subCategoryId,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime startDateTime,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime endDateTime
) {
    public static SearchCommand of(SearchRequest searchRequest, Long employeeId){
        return new SearchCommand(
                employeeId,
                searchRequest.sido(),
                searchRequest.sigugun(),
                searchRequest.dong(),
                searchRequest.subCategoryId(),
                searchRequest.getStartDateTime(),
                searchRequest.getEndDateTime()
        );
    }
}




