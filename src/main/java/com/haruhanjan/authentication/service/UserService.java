package com.haruhanjan.authentication.service;

import com.haruhanjan.authentication.dto.CreateUserRequestDto;
import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.entity.User;
import com.haruhanjan.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    private final TokenProvider tokenProvider;
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public List<UserResponseDto> getAll() {
        List<User> targets =userRepository.findAll();
        return targets.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
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

//    public JWTTokenDto login(LoginRequestDTO dto) {
//        User target = userRepository.findByAccountId(dto.getAccountId()).orElseThrow(EntityNotFoundException::new);
//        if (!passwordEncoder.matches(dto.getPassword(), target.getPassword()))
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 맞지 않습니다!");
//
//        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();
//        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        // 3. 인증 정보를 기반으로 Jwt 토큰 생성
//        JWTTokenDto tokenDto = tokenProvider.createToken(authentication);
//        // refresh token -> redis 저장
//
//        return tokenDto;
//    }

    public User saveIfNone(OAuthUserDTO dto){
        return userRepository.findByAccountId(dto.getAccountId())
                .orElseGet(() -> userRepository.save(dto.toEntity()));
    }
}
