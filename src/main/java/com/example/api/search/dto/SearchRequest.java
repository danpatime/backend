package com.example.api.search.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record SearchRequest(
        String sido,
        String sigugun,
        String dong,
        Long subCategoryId,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate date,
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime startTime,
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime endTime
) {
    public LocalDateTime getStartDateTime() {
        if (this.date == null || this.startTime == null) {
            return null;
        }
        return LocalDateTime.of(this.date, this.startTime);
    }


    public LocalDateTime getEndDateTime() {
        if (this.date == null || this.endTime == null) {
            return null;
        }
        return LocalDateTime.of(this.date, this.endTime);
    }
}