package com.haruhanjan.authentication.service.oauth2;

import com.haruhanjan.authentication.service.oauth2.userMapper.KakaoUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoUserMapper kakaoUserMapper;
}
