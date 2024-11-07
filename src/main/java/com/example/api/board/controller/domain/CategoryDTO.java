package com.example.api.board.controller.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
}
