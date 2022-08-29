package com.haruhanjan.authentication.service.oauth2.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoUserMapper kakaoUserMapper;
}
