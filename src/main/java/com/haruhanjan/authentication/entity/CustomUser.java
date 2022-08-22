package com.haruhanjan.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String nickname;

    private Integer age;

    @Embedded
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    @Builder
    public CustomUser(String email, String password, String name, String nickname, Integer age) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.baseTimeEntity = new BaseTimeEntity();
    }

    public void delete() {
        baseTimeEntity.delete();
    }
}
