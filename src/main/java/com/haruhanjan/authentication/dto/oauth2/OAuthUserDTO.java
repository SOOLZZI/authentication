package com.haruhanjan.authentication.dto.oauth2;

import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuthUserDTO {
    private String accountId;
    private String name;
    private String email;
    private AuthProvider authProvider;

    public User toEntity() {
        return User.builder()
                .accountId(accountId)
                .name(name)
                .email(email)
                .authProvider(authProvider)
                .build();
    }
}
