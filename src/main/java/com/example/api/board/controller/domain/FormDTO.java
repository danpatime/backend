package com.example.api.board.controller.domain;

import com.example.api.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FormDTO {
    private MyInfoDTO myInfo;
    private List<CategoryDTO> categoryList;
}
