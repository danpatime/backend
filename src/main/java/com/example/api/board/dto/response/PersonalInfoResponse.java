package com.example.api.board.dto.response;

import com.example.api.domain.Account;
import com.example.api.domain.Location;

public record PersonalInfoResponse(
        String name,
        String nickname,
        Integer age,
        String sex,
        String email,
        String phoneNumber,
        Float starPoint,
        Integer workCount,
        String profile,
        String birthdate,
        String callTime,
        Location location
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
                user.getWorkCount(),
                user.getProfileImage(),
                user.getBirthdate(),
                user.getCallTime(),
                user.getLocation()
        );
    }

}