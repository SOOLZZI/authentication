package com.haruhanjan.authentication.dto;

import com.haruhanjan.authentication.entity.CustomUser;
import lombok.Getter;
import lombok.Setter;

@Setter
public class CreateUserRequestDto {
    private String email;
    @Getter
    private String password;
    private String name;
    private String nickname;
    private Integer age;

    public CustomUser toEntity(String encodedPassword) {
        return CustomUser.builder()
                .email(this.email)
                .password(encodedPassword)
                .age(this.age)
                .name(this.name)
                .nickname(this.nickname)
                .build();
    }
}
