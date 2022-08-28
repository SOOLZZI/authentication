package com.haruhanjan.authentication.config.oauth2;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class OAuth2Config {
    private final String redirectURL;
    private final String getUserInfoURL;
}
