package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.service.oauth2.GoogleLoginService;
import com.haruhanjan.authentication.service.oauth2.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/auth")
public class LoginController {

    private final KakaoLoginService kakaoLoginService;
    private final GoogleLoginService googleLoginService;
    //private final NaverLoginService naverLoginService;
    //private final GithubLoginService githubLoginService;

    @GetMapping("kakao")
    public void redirectKakaoLoginUrl(@NotNull HttpServletResponse response) throws IOException {
        response.sendRedirect("");
    }

    @GetMapping("google")
    public String redirectGoogleLoginUrl(@NotNull HttpServletResponse response) throws IOException {
        response.sendRedirect("");
        return null;
    }

}
