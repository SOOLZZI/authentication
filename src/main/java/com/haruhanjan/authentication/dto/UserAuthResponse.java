package com.haruhanjan.authentication.dto;


import com.haruhanjan.authentication.entity.User;
import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class UserAuthResponse {
    public Long id;
    public String accountId;
    public String nickname;
    public String role;

    public UserAuthResponse(User user) {
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.nickname = user.getNickname();
        this.role = user.getAuthority().name();
    }

    public UserAuthResponse(Claims claims) {
        this.id = Long.parseLong(claims.get("id").toString());
        this.accountId = claims.get("accountId").toString();
        this.nickname = claims.get("nickname").toString();
        this.role = claims.get("role").toString();
    }

    public UserAuthResponse(Long id, String accountId, String nickname, String role) {
        this.id = id;
        this.accountId = accountId;
        this.nickname = nickname;
        this.role = role;
    }
}
