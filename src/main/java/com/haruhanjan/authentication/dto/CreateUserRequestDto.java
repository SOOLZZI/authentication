package com.haruhanjan.authentication.dto;

import com.haruhanjan.authentication.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
public class CreateUserRequestDto {
    @Getter
    private String accountId;
    @Getter
    private String password;
    private String email;
    private String name;
    private String nickname;
    private Integer age;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .accountId(this.accountId)
                .email(this.email)
                .password(encodedPassword)
                .age(this.age)
                .name(this.name)
                .nickname(this.nickname)
                .build();
    }
}
