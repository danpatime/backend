package com.example.api.board.dto.update;

import com.example.api.domain.Location;

public record UpdatePersonalInfoRequest(
        String name,
        String sex,
        Integer age,
        String phoneNumber,
        String email,
        String nickname,
        String birthdate,
        String callTime,
        Location location
) implements UpdateAccountConditionCommand{
}
