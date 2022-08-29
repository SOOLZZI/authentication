package com.haruhanjan.authentication.config.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class GoogleOauth2Config {

    @Value("${oauth.backend-base-url}/api/auth/google/callback")
    private String callbackURL;

    @Value("${oauth.backend-base-url}/api/auth/google/user")
    private String callbackURL2;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String scope;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    private final String REDIRECT_URL = "https://accounts.google.com/o/oauth2/v2/auth?client_id=%s&response_type=code&redirect_uri=%s&scope=%s";

    private final String TOKEN_URL = "https://oauth2.googleapis.com/token?code=%s&client_id=%s&client_secret=%s&grant_type=authorization_code&redirect_uri=%s";
    private final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json?access_token=%s";

    public String getRedirectURL(){

        return String.format(REDIRECT_URL, clientId, callbackURL, scope);
    }

    public Map<String, String> getRequestTokenParam(String code){
        Map<String, String> data = new HashMap<>();

        data.put("code", code);
        data.put("client_id", clientId);
        data.put("client_secret", clientSecret);
        data.put("redirect_uri", callbackURL2);
        data.put("grant_type", "authorization_code");

        return data;
    }

    public String requestTokenURL(String code) {
        //String result = String.format(TOKEN_URL, code, clientId, clientSecret, callbackURL2);
        String result = "https://oauth2.googleapis.com/token";
        log.info("requestTokenURL: {}", result);

        return result;
    }


    public String requestUserInfoURL(String accessToken) {
        return String.format(USER_INFO_URL, accessToken);
    }
}
