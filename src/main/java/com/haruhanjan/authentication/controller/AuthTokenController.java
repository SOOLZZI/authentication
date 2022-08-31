package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @PostMapping("logout")
    public void logout(HttpServletResponse response) throws IOException {
        authService.logout(response);
        response.sendRedirect("/");
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
