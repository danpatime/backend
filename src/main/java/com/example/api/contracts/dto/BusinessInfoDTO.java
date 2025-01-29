package com.example.api.contracts.dto;

import com.example.api.business.domain.BusinessLocation;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfoDTO {
    private String businessName;
    private String representationName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BusinessLocation location;
    private String businessPhone;
    private LocalDateTime signedDate;
}
