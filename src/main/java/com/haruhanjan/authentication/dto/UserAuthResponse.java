package com.haruhanjan.authentication.dto;


import com.haruhanjan.authentication.entity.User;
import io.jsonwebtoken.Claims;
import lombok.Getter;

@Getter
public class UserAuthResponse {

    public String accountId;
    public String role;

    public UserAuthResponse(User user) {
        this.accountId = user.getAccountId();
        this.role = user.getAuthority().name();
    }

    public UserAuthResponse(Claims claims) {
        this.accountId = claims.get("accountId").toString();
        this.role = claims.get("role").toString();
    }

    public UserAuthResponse(String accountId, String role) {
        this.accountId = accountId;
        this.role = role;
    }
}
