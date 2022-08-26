package com.haruhanjan.authentication.service.oauth2.userMapper;

import com.haruhanjan.authentication.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GithubUserMapper implements UserMapper {

    @Override
    public User map(Map<String, Object> attributes) {
        return null;
    }
}