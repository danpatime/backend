package com.example.api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String nickname;
}