package com.example.api.contracts.dto;

import com.example.api.business.domain.BusinessLocation;
import java.time.LocalDateTime;

public record BusinessInfoDTO (
    String businessName,
    String representationName,
    LocalDateTime startTime,
    LocalDateTime endTime,
    BusinessLocation location,
    String businessPhone,
    LocalDateTime signedDate){
}