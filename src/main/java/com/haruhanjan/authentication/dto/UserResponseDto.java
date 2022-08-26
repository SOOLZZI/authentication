package com.haruhanjan.authentication.dto;

import com.haruhanjan.authentication.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {
    private String accountId;
    private String email;
    private String name;
    private String nickname;
    private Integer age;

    public UserResponseDto(User target) {
        this.email = target.getEmail();
        this.accountId = target.getEmail();
        this.age = target.getAge();
        this.nickname = target.getNickname();
        this.name = target.getName();
    }
}
