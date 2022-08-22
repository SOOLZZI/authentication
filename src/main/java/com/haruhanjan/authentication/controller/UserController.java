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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody CreateUserRequestDto dto) {
        UserResponseDto result = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
