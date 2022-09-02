package com.haruhanjan.authentication.service.oauth2.google;

import com.haruhanjan.authentication.dto.oauth2.OAuthUserDTO;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.entity.Authority;
import com.haruhanjan.authentication.entity.User;
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
    public Map<String, Object> mapToTokenAttribute(User user) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("accountId", user.getAccountId());
        result.put("nickname", user.getNickname());
        result.put("role", Authority.ROLE_USER);
        return result;
    }
}