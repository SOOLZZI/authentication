package com.haruhanjan.authentication.service.oauth2;

import com.haruhanjan.authentication.service.oauth2.github.GithubUserMapper;
import com.haruhanjan.authentication.service.oauth2.google.GoogleUserMapper;
import com.haruhanjan.authentication.service.oauth2.kakao.KakaoUserMapper;
import com.haruhanjan.authentication.service.oauth2.naver.NaverUserMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserMapperFactory {

    private Map<String, UserMapper> oAuth2ServiceMap = new HashMap<>();

    public UserMapperFactory() {
        oAuth2ServiceMap.put("google",new GoogleUserMapper());
        oAuth2ServiceMap.put("kakao", new KakaoUserMapper());
        oAuth2ServiceMap.put("naver", new NaverUserMapper());
        oAuth2ServiceMap.put("github", new GithubUserMapper());
    }

    public UserMapper userMapper(String qualifier) {
        return oAuth2ServiceMap.get(qualifier);
    }
}
