package com.example.api.board.controller.domain;

import com.example.api.domain.Category;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.PossibleBoard;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class MyInfoDTO {
    private String name;
    private String nickname;
    private int age;
    private String sex;
    private String email;
    private String phone;
    private List<InnerCareerDTO> innerCarrerList;
    private List<ExternalCareerDTO> externalCareerList;
    private List<PossibleBoardDTO> possibleBoardList;
    private List<CategoryDTO> flavoredCategoryList;
    private float starPoint;
    private int workCount;

    public MyInfoDTO(String name, String nickname, int age, String sex, String email, String phone, float starPoint, int workCount) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.starPoint = starPoint;
        this.workCount = workCount;
    }
}