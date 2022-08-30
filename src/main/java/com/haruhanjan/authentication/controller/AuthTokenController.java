package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthTokenController {
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<UserAuthResponse> auth(@CookieValue String access_token) {
        UserAuthResponse loginUser = authService.validateToken(access_token);
        return ResponseEntity.ok(loginUser);
    }

    @GetMapping("refresh")
    public ResponseEntity<Void> refreshAuth(@CookieValue String refresh_token,
                                            HttpServletResponse response) {
        Cookie accessTokenCookie = new Cookie("access_token", authService.reissue(refresh_token));
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(accessTokenCookie);
        return ResponseEntity.ok().build();
    }

}
