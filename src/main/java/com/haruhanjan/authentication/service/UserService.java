package com.haruhanjan.authentication.service;

import com.haruhanjan.authentication.dto.CreateUserRequestDto;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.entity.CustomUser;
import com.haruhanjan.authentication.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getAll() {
        List<CustomUser> targets =customUserRepository.findAll();
        return targets.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto save(CreateUserRequestDto dto) {
        CustomUser entity = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        CustomUser saved = customUserRepository.save(entity);
        return new UserResponseDto(saved);
    }

    public void delete(Long id) {
        CustomUser target =customUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.delete();
    }
}
