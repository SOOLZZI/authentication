package com.haruhanjan.authentication.config.security.jwt;

import com.haruhanjan.authentication.dto.TokenDto;
import com.haruhanjan.authentication.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final Key hashKey;
    private final Long ACCESS_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 1000;
    private final Long REFRESH_TOKEN_VALIDATION_SECOND = 60L * 60 * 24 * 14 * 1000;

    public TokenDto createToken(User user) {
        Claims claims = getClaims(user);

        String accessToken = getToken(user, claims, ACCESS_TOKEN_VALIDATION_SECOND);
        String refreshToken = getToken(user, claims, REFRESH_TOKEN_VALIDATION_SECOND);

        return TokenDto.builder()
                .grantType("bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    
    private Claims getClaims(User user) {
        Claims claims = Jwts.claims();
        claims.put("accountId", user.getAccountId());
        claims.put("role", user.getAuthority().name());

        return claims;
    }

    private String getToken(User user, Claims claims, Long TOKEN_VALIDATION_SECOND) {
        long now = new Date().getTime();

        return Jwts.builder()
                .setSubject(user.getAccountId())
                .setClaims(claims)
                .signWith(hashKey, SignatureAlgorithm.ES512)
                .setExpiration(new Date(now + TOKEN_VALIDATION_SECOND))
                .compact();
    }
}
