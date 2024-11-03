package com.example.api.board.controller.domain;

import com.example.api.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class InnerCarrer {
    private String businessName;
    private Date workDate;
    private String representationName;
    private Review review;
}
