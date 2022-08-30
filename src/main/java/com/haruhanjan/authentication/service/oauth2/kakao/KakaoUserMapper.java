package com.haruhanjan.authentication.service.oauth2.kakao;

import com.haruhanjan.authentication.dto.oauth2.OAuthUserDTO;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.entity.Authority;
import com.haruhanjan.authentication.service.oauth2.UserMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KakaoUserMapper implements UserMapper {
    @Override
    public OAuthUserDTO mapToDTO(Map<String, Object> attributes) {
        String accountId = ((Integer)attributes.get("id")).toString();
        String email = (String) attributes.get("email");
        String name = "sample";
        return new OAuthUserDTO(accountId, name, email, AuthProvider.KAKAO);
    }

    @Override
    public Map<String, Object> mapToTokenAttribute(Map<String, Object> attributes) {
        Map<String, Object> result = new HashMap<>();
        result.put("accountId", ((Integer)attributes.get("id")).toString());
        result.put("role", Authority.ROLE_USER);
        return result;
    }
}