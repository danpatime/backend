package com.example.api.board.controller.domain;

import com.example.api.domain.Category;
import com.example.api.domain.ExternalCareer;
import com.example.api.domain.PossibleBoard;

import java.util.List;

public class MyInfoDTO {
    private String name;
    private String nickname;
    private int age;
    private String sex;
    private String email;
    private String phone;
    private List<InnerCarrer> innerCarrerList;
    private List<ExternalCareer> externalCareerList;
    private List<PossibleBoard> possibleBoardList;
    private List<Category> flavoredCategoryList;
    private float starPoint;
    private int workCount;
}