package com.haruhanjan.authentication.service.oauth2;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OAuth2ServiceFactory {

    private Map<String, OAuth2Service> oAuth2ServiceMap = new HashMap<>();

    public OAuth2ServiceFactory() {
        oAuth2ServiceMap.put("google",new GoogleOAuth2Service());
        oAuth2ServiceMap.put("kakao", new KakaoOAuth2Service());
        oAuth2ServiceMap.put("naver", new NaverOAuth2Service());
    }

    public OAuth2Service oAuth2Service(String qualifier) {
        return oAuth2ServiceMap.get(qualifier);
    }
}
