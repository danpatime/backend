package com.example.api.board.controller.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
}
