package com.haruhanjan.authentication.dto;

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

//    public User toEntity(String encodedPassword) {
//        return User.builder()
//                .email(this.email)
//                .password(encodedPassword)
//                .age(this.age)
//                .name(this.name)
//                .nickname(this.nickname)
//                .build();
//    }
}
