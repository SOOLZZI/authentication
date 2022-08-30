package com.haruhanjan.authentication.service.oauth2.google;

import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.entity.Authority;
import com.haruhanjan.authentication.service.oauth2.UserMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class GoogleUserMapper implements UserMapper {
    @Override
    public OAuthUserDTO mapToDTO(Map<String, Object> attributes) {
        String accountId = (String)attributes.get("sub");
        String name = (String)attributes.get("name");
        String email = (String)attributes.get("email");
        return new OAuthUserDTO(accountId, name, email, AuthProvider.GOOGLE);
    }

    @Override
    public Map<String, Object> mapToTokenAttribute(Map<String, Object> attributes) {
        Map<String, Object> result = new HashMap<>();
        result.put("accountId", attributes.get("sub"));
        result.put("role", Authority.ROLE_USER);
        return result;
    }
}