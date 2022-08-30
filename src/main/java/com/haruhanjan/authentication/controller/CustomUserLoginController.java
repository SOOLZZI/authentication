package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.dto.CreateUserRequestDto;
import com.haruhanjan.authentication.dto.JWTTokenDto;
import com.haruhanjan.authentication.dto.LoginRequestDTO;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.service.AuthService;
import com.haruhanjan.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class CustomUserLoginController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> join(@RequestBody CreateUserRequestDto dto) {
        UserResponseDto result = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PostMapping("login")
    public ResponseEntity<JWTTokenDto> login(@RequestBody LoginRequestDTO dto,
                                             HttpServletResponse response) throws AuthException {
        JWTTokenDto tokenDto = authService.getJwtToken(dto);
        authService.addJwtTokensInCookie(response, tokenDto);
        return ResponseEntity.ok(tokenDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}