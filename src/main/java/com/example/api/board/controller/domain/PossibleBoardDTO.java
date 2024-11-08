package com.example.api.board.controller.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PossibleBoardDTO {
    private long id;

    @EqualsAndHashCode.Include
    private LocalDateTime startTime;

    @EqualsAndHashCode.Include
    private LocalDateTime endTime;
}
