package com.example.api.board.controller.domain;

import com.example.api.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class InnerCarrer {
    private String businessName;
    private LocalDate workDate;
    private String representationName;
    private Review review;
}
