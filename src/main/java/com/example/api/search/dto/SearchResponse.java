package com.example.api.search.dto;

import com.example.api.board.dto.response.ExternalCareerResponse;
import com.example.api.board.dto.response.FlavoredCategoryResponse;
import com.example.api.board.dto.response.FlavoredDistrictResponse;

import java.util.List;

public record SearchResponse(
        Long employeeId,
        String name,
        String sex,
        Integer age,
        Float starPoint,
        Integer workCount,
        List<ExternalCareerResponse> externalCareerList,
        List<FlavoredCategoryResponse> flavoredCategoryList,
        List<FlavoredDistrictResponse> flavoredDistrictList
) {
    public SearchResponse(Long employeeId, String name, String sex, Integer age, Float starPoint, Integer workCount) {
        this(employeeId, name, sex, age, starPoint, workCount, List.of(), List.of(), List.of());
    }

    public SearchResponse(Long employeeId, String name, String sex, Integer age, Float starPoint, Integer workCount, List<ExternalCareerResponse> externalCareerList, List<FlavoredCategoryResponse> flavoredCategoryList, List<FlavoredDistrictResponse> flavoredDistrictList) {
        this.employeeId = employeeId;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.starPoint = starPoint;
        this.workCount = workCount;
        this.externalCareerList = externalCareerList;
        this.flavoredCategoryList = flavoredCategoryList;
        this.flavoredDistrictList = flavoredDistrictList;
    }
}