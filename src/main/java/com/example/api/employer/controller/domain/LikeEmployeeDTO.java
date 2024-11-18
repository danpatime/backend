package com.example.api.employer.controller.domain;

import com.example.api.board.controller.domain.response.CategoryDTO;
import com.example.api.board.controller.domain.response.ExternalCareerDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class LikeEmployeeDTO {
    private long employeeId;
    private String name;
    private String nickname;
    private String sex;
    private int age;
    private float starPoint;
    private long workCount;
    private List<ExternalCareerDTO> externalCareerList;
    private List<CategoryDTO> flavoredCategoryList;
}
