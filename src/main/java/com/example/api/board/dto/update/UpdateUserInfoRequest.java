package com.example.api.board.dto.update;

public record UpdateUserInfoRequest(
        String name,
        String sex,
        Integer age,
        String phoneNumber,
        String email,
        String nickname
) implements UpdateAccountConditionCommand{
}
