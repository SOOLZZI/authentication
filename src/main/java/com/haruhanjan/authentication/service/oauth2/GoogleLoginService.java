package com.haruhanjan.authentication.service.oauth2;

import com.haruhanjan.authentication.config.oauth2.GoogleOauth2Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleLoginService {
    private final GoogleOauth2Config googleOauth2Config;
    public String getRedirectURL() {
        return googleOauth2Config.getRedirectURL();
    }
}
