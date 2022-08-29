package com.haruhanjan.authentication.service.oauth2.kakao;

import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.service.oauth2.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KakaoUserMapper implements UserMapper {
    @Override
    public OAuthUserDTO map(Map<String, Object> attributes) {
        String accountId = ((Integer)attributes.get("id")).toString();
        String email = (String) attributes.get("email");
        String name = "sample";
        return new OAuthUserDTO(accountId, name, email, AuthProvider.KAKAO);
    }
}