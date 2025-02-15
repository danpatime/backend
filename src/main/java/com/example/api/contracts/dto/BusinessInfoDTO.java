package com.example.api.contracts.dto;

import com.example.api.domain.Location;

import java.time.LocalDateTime;

public record BusinessInfoDTO (
    String businessName,
    String representationName,
    LocalDateTime startTime,
    LocalDateTime endTime,
    Location location,
    String businessPhone,
    LocalDateTime signedDate){
}