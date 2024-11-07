package com.example.api.board.controller.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class PossibleBoardDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
