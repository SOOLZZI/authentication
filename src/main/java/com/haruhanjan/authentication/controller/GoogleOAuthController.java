package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.service.oauth2.GoogleLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/google")
@Slf4j
public class GoogleOAuthController {

    private final GoogleLoginService googleLoginService;

    //구글 로그인 페이지로 리다이렉트
    @GetMapping
    public void redirectGoogleLoginUrl(@NotNull HttpServletResponse response) throws IOException {
        response.sendRedirect(googleLoginService.getRedirectURL());
    }

    @GetMapping("callback")
    public String authGoogleCallback() {

        log.info("나는 콜백이야~");
        return null;
    }
    // 정보 받는 api
    // 호출 이후 json 처리
    // google access token은 정보 받고 팽
    // 자체 토큰 발급
}
