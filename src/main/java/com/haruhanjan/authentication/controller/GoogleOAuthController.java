package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.service.oauth2.google.GoogleLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void authGoogleCallback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        log.info("code: {}", code);
        // google access token 발급 요청 post

        response.sendRedirect(googleLoginService.requestTokenURL(code));
    }

    @GetMapping("user")
    public String authGoogleCallback(HttpServletResponse response) throws IOException {
        log.info("{}", response.toString());

        return "ok";
    }
    
    // 정보 받는 api
    // 호출 이후 json 처리
    // google access token은 정보 받고 팽
    // 자체 토큰 발급
}
