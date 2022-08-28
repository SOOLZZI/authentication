package com.haruhanjan.authentication.config.security.jwt;

import com.haruhanjan.authentication.dto.UserAuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final Key key;

    public UserAuthResponse getAuthentication(String accessToken) {
        Claims claims = getTokenBodyClaims(accessToken);

        return new UserAuthResponse(claims);
    }

    private Claims getTokenBodyClaims(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

}
