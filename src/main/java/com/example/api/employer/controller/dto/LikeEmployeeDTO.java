package com.example.api.employer.controller.dto;

import com.example.api.board.dto.response.ExternalCareerResponse;
import com.example.api.board.dto.response.FlavoredCategoryResponse;
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
    private List<ExternalCareerResponse> externalCareerList;
    private List<FlavoredCategoryResponse> flavoredCategoryList;
}
