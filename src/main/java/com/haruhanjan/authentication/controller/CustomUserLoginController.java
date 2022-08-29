package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.dto.CreateUserRequestDto;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class CustomUserLoginController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/join")
    public ResponseEntity<UserResponseDto> join(@RequestBody CreateUserRequestDto dto) {
        UserResponseDto result = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
//    @PostMapping("/login")
//    public ResponseEntity<JWTTokenDto> login(@RequestBody LoginRequestDTO dto,
//                                             HttpServletResponse response) {
//       // JWTTokenDto tokenDto = userService.login(dto);
//
//        // 발급받은 토큰 쿠키 설정
//        Cookie refreshTokenCookie = new Cookie("jwt_refresh_token", tokenDto.getRefreshToken());
//        Cookie accessTokenCookie = new Cookie("jwt_access_token", tokenDto.getAccessToken());
//        accessTokenCookie.setPath("/");
//        refreshTokenCookie.setPath("/");
//        response.addCookie(accessTokenCookie);
//        response.addCookie(refreshTokenCookie);
//
//        //return ResponseEntity.ok(tokenDto);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}