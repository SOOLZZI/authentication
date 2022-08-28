package com.haruhanjan.authentication.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler({
            io.jsonwebtoken.security.SecurityException.class,
            MalformedJwtException.class
    })
    public ResponseEntity<String> malformedJwtToken(IllegalArgumentException e1,
                                                  MalformedJwtException e2) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("잘못된 JWT 서명입니다.");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> expiredJwtToken(ExpiredJwtException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("만료된 JWT 토큰입니다.");
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<String> unsupportedJwtToken(UnsupportedJwtException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("지원되지 않는 JWT 토큰입니다.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalJwtToken(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("JWT 토큰이 잘못되었습니다.");
    }
}
