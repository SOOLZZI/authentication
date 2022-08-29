package com.haruhanjan.authentication.service.oauth2.naver;

import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.service.oauth2.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class NaverUserMapper implements UserMapper {

    @Override
    public OAuthUserDTO map(Map<String, Object> attributes) {

        Map<String, Object> response =
                (Map<String, Object>)attributes.get("response");

        String accountId = (String)response.get("id");
        String name = (String)response.get("name");
        String email = (String)response.get("email");
        return new OAuthUserDTO(accountId, name, email, AuthProvider.NAVER);
    }

}
