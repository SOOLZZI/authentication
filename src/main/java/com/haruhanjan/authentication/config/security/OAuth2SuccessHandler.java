package com.haruhanjan.authentication.config.security;


import com.haruhanjan.authentication.config.security.jwt.JwtTokenProvider;
import com.haruhanjan.authentication.dto.JWTTokenDto;
import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.entity.Authority;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException, java.io.IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String loginUserAccountId = oAuth2User.getAttribute("accountId");

        String loginUserRole = ((Authority)oAuth2User.getAttribute("role")).name();

        UserAuthResponse user = new UserAuthResponse(loginUserAccountId, loginUserRole);
        JWTTokenDto jwtTokenDto = jwtTokenProvider.createJWTTokens(user);

        Cookie accessCookie = new Cookie("access_token", jwtTokenDto.getAccessToken());
        accessCookie.setPath("/");
        accessCookie.setHttpOnly(true);
        response.addCookie(accessCookie);

        Cookie refreshCookie = new Cookie("refresh_token", jwtTokenDto.getRefreshToken());
        refreshCookie.setPath("/");
        refreshCookie.setHttpOnly(true);
        response.addCookie(refreshCookie);

        response.sendRedirect("/");
    }
}
