package com.haruhanjan.authentication.service;

import com.haruhanjan.authentication.dto.CreateUserRequestDto;
import com.haruhanjan.authentication.dto.LoginRequestDTO;
import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.dto.oauth2.OAuthUserDTO;
import com.haruhanjan.authentication.entity.User;
import com.haruhanjan.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;
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

    public UserAuthResponse verifyLogin(LoginRequestDTO dto) throws AuthException {
        User user = userRepository.findByAccountId(dto.getAccountId())
                .orElseThrow(EntityNotFoundException::new);
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new AuthException();
        }

        return new UserAuthResponse(user);
    }

    public UserResponseDto save(CreateUserRequestDto dto) {
        userRepository.findByAccountId(dto.getAccountId())
                    .ifPresent(u -> {throw new EntityExistsException();});

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        User saved = userRepository.save(dto.toEntity());
        return new UserResponseDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        User target = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.delete();
    }

    public User saveIfNone(OAuthUserDTO dto){
        return userRepository.findByAccountId(dto.getAccountId())
                .orElseGet(() -> userRepository.save(dto.toEntity()));
    }
}
