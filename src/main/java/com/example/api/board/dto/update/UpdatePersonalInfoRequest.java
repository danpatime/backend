package com.example.api.board.dto.update;

public record UpdatePersonalInfoRequest(
        String name,
        String sex,
        Integer age,
        String phoneNumber,
        String email,
        String nickname,
        String birthdate,
        String callTime
) implements UpdateAccountConditionCommand{
}
