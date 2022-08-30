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

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    private final RefreshTokenRepository refreshTokenRepository;

    // 1. CustomUser 로그인
    public JWTTokenDto login(LoginRequestDTO dto) throws AuthException {
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

    // 4. 토큰 삭제
}
