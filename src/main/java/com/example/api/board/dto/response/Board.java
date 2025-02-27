package com.example.api.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Board {
    private MyInfoDTO myInfo;
    private List<CategoryDTO> categoryList;
}
