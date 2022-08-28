package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.config.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthTokenController {
    private final TokenProvider tokenProvider;

    @GetMapping
    public ResponseEntity<Long> auth() {
        // 검증 로직
        Long loginUserId = null;

        return ResponseEntity.ok(loginUserId);
    }

    @GetMapping("refresh")
    public String refreshAuth() {
        return null;
    }


}
