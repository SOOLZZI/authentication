package com.haruhanjan.authentication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
public class User implements OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String accountId;
    // default -> phjppo0918
    // social -> "id" or "at_hash"

    private String email; // 회원아이디? ex@ex.com?

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
