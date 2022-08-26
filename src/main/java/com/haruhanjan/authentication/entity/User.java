package com.haruhanjan.authentication.entity;

import lombok.Builder;
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
    private String password;
    private String email; // ex@ex.com?
    private String name;
    private String nickname;
    private Integer age;

    @Builder.Default
    private boolean isAdult = false;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AuthProvider authProvider = AuthProvider.NONE;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Authority authority = Authority.ROLE_USER;

    @Embedded
    @Builder.Default
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    @Builder
    public User(String accountId,String email, String password, String name, String nickname, Integer age) {
        this.accountId=accountId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
    }
    public void update() {
        baseTimeEntity.update();
    }
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
