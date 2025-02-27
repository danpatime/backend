package com.example.api.board.dto.response;

import com.example.api.domain.Review;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class InnerCareerDTO {
    private String businessName;
    private LocalDate workDate;
    private String representationName;
    private Review review;

    public InnerCareerDTO(String businessName, LocalDateTime startTime, String representationName, Review review) {
        this.businessName = businessName;
        this.workDate = startTime.toLocalDate();
        this.representationName = representationName;
        this.review = review;
    }
}
