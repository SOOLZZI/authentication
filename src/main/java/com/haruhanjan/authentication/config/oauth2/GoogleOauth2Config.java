package com.haruhanjan.authentication.config.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GoogleOauth2Config {

    @Value("${oauth.backend-base-url}/auth/google/callback")
    private String callbackURL;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String scope;

    String redirectURL = "https://accounts.google.com/o/oauth2/v2/auth?client_id=%s&response_type=code&redirect_uri=%s&scope=%s";
    String userInfoURL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json?access_token=%s";

    public String getRedirectURL(){

        return String.format(redirectURL, clientId, callbackURL, scope);
    }

    public String getUserInfoURL(String accessToken) {
        return String.format(userInfoURL, accessToken);
    }
}
