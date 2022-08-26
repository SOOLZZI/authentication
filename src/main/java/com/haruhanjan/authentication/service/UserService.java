package com.haruhanjan.authentication.service;

import com.haruhanjan.authentication.dto.CreateUserRequestDto;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.entity.User;
import com.haruhanjan.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getAll() {
        List<User> targets =userRepository.findAll();
        return targets.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto save(CreateUserRequestDto dto) {
        User entity = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        User saved = customUserRepository.save(entity);
        return new UserResponseDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        User target =customUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.delete();
    }
}
