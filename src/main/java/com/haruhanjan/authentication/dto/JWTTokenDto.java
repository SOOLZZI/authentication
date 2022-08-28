package com.haruhanjan.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class JWTTokenDto {
    private String accessToken;
    private String refreshToken;
    private String grantType;
}
