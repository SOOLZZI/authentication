package com.haruhanjan.authentication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User implements OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String accountId;
    // naver -> N123lsdkfji341234

    private String email; // 회원아이디? ex@ex.com?
    // 1. 이메일 + 소셜
    // 2. 이메일

    // 소셜 로그인 중복 가입을 허용할 것인가? ㅇㅇ?
    //소셜 로그인시에는 이게 필요가 있음!! 기존/신규
    // 상속->고민X, 굳이 ,Kakao/navOauth2User extends CustomUser   중복확인
    private String password;
    private String name;
    private String nickname;
    private Integer age;

    private boolean isAdult = false;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider = AuthProvider.NONE;

    @Embedded
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

//    @Builder
//    public User(String email, String password, String name, String nickname, Integer age) {
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.nickname = nickname;
//        this.baseTimeEntity = new BaseTimeEntity();
//    }

    public void delete() {
        baseTimeEntity.delete();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
