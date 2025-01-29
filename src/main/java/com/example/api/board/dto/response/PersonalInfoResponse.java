package com.example.api.board.dto.response;

import com.example.api.domain.Account;

public record PersonalInfoResponse(
        String name,
        String nickname,
        Integer age,
        String sex,
        String email,
        String phoneNumber,
        Float starPoint,
        Integer workCount
) {
    public static PersonalInfoResponse of(Account user){
        return new PersonalInfoResponse(
                user.getName(),
                user.getNickname(),
                user.getAge(),
                user.getSex(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getStarPoint(),
                user.getWorkCount()
        );
    }
}