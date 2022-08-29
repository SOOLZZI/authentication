package com.haruhanjan.authentication.service.oauth2.google;

import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.service.oauth2.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class GoogleUserMapper implements UserMapper {
    @Override
    public OAuthUserDTO map(Map<String, Object> attributes) {
        String accountId = (String)attributes.get("sub");
        String name = (String)attributes.get("name");
        String email = (String)attributes.get("email");
        return new OAuthUserDTO(accountId, name, email, AuthProvider.GOOGLE);
    }
}