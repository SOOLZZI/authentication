package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.config.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthTokenController {
    private final TokenProvider tokenProvider;

    @GetMapping
    public String auth() {
        return null;
    }

    @GetMapping("refresh")
    public String refreshAuth() {
        return null;
    }


}
