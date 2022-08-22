package com.haruhanjan.authentication.dto;

import com.haruhanjan.authentication.entity.CustomUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserResponseDto {
    private String email;
    private String name;
    private String nickname;
    private Integer age;

    public UserResponseDto (CustomUser target){
        this.email=target.getEmail();
        this.age= target.getAge();
        this.nickname= target.getNickname();
        this.name=target.getName();
    }
}
