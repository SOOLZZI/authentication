package com.haruhanjan.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

public class KakaoOAuthController {
    @GetMapping("kakao")
    public void redirectKakaoLoginUrl(@NotNull HttpServletResponse response) throws IOException {
        response.sendRedirect("");
    }

}
