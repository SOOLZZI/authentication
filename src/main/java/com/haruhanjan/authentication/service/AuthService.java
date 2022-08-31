package com.haruhanjan.authentication.service;

import com.haruhanjan.authentication.config.security.jwt.JwtTokenProvider;
import com.haruhanjan.authentication.config.security.jwt.JwtTokenValidator;
import com.haruhanjan.authentication.dto.JWTTokenDto;
import com.haruhanjan.authentication.dto.LoginRequestDTO;
import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    private final RefreshTokenRepository refreshTokenRepository;

    // 1. CustomUser 로그인
    public JWTTokenDto getJwtToken(LoginRequestDTO dto) throws AuthException {
        UserAuthResponse user = userService.verifyLogin(dto);
        JWTTokenDto tokenDto = jwtTokenProvider.createJWTTokens(user);
        // refresh token -> redis 저장 TODO
        return tokenDto;
    }

    // 2. 토큰이 유효성 검증
    public UserAuthResponse validateToken(String token){
        return jwtTokenValidator.getAuthentication(token);
    }

    // 3. 리프레시
    public String reissue(String refreshToken) {

        //refreshTokenRepository.isExist(refreshToken)
                //.orElseThrow(IllegalArgumentException::new);
        UserAuthResponse user = jwtTokenValidator.getAuthentication(refreshToken);
        return jwtTokenProvider.reissueAccessToken(user);
    }

    public void addJwtTokensInCookie(HttpServletResponse response, JWTTokenDto tokenDto) {
        // 발급받은 토큰 쿠키 설정 addJwtTokenInCookie
        Cookie accessTokenCookie = setCookie("access_token", tokenDto.getAccessToken());
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = setCookie("refresh_token", tokenDto.getRefreshToken());
        response.addCookie(refreshTokenCookie);
    }

    private Cookie setCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    // 4. 토큰 삭제
    public void logout(HttpServletResponse response) {
        expireCookie(response,"access_token");
        expireCookie(response,"refresh_token");
    }

    private void expireCookie(HttpServletResponse response, String key) {
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
}
