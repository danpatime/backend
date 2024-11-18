package com.example.api.board.controller.domain.response;

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
