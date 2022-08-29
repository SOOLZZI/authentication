package com.haruhanjan.authentication.service.oauth2.github;

import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.service.oauth2.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GithubUserMapper implements UserMapper {

    @Override
    public OAuthUserDTO map(Map<String, Object> attributes) {
        return null;
    }
}