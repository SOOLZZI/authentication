package com.haruhanjan.authentication.config.security;


import com.haruhanjan.authentication.config.security.jwt.JwtTokenProvider;
import com.haruhanjan.authentication.dto.JWTTokenDto;
import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.entity.Authority;
import com.haruhanjan.authentication.service.AuthService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthService authService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException, java.io.IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String loginUserAccountId = oAuth2User.getAttribute("accountId");

        String loginUserRole = ((Authority)oAuth2User.getAttribute("role")).name();

        Long loginUserId =  oAuth2User.getAttribute("id");

        String loginUserNickname = oAuth2User.getAttribute("nickname");

        UserAuthResponse user = new UserAuthResponse(loginUserId, loginUserAccountId,loginUserNickname ,loginUserRole);
        JWTTokenDto jwtTokenDto = jwtTokenProvider.createJWTTokens(user);

        authService.addJwtTokensInCookie(response, jwtTokenDto);

        response.sendRedirect("/");
    }
}
