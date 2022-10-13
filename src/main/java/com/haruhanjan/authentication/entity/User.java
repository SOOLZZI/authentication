package com.haruhanjan.authentication.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String accountId;
    private String email; // ex@ex.com?
    private String password;
    private String name;
    private String nickname;
    private Integer age;

    private boolean isAdult = false;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider = AuthProvider.NONE;

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.ROLE_USER;

    @Embedded
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    @Builder
    public User(String accountId,String email, String password, String name, String nickname, Integer age, AuthProvider authProvider) {
        this.accountId=accountId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.authProvider = authProvider;
    }
    public void update() {
        baseTimeEntity.update();
    }
    public void delete() {
        baseTimeEntity.delete();
    }

}
