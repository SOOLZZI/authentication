package com.haruhanjan.authentication.config.security;


import com.haruhanjan.authentication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // UserDetailService 안 쓰는 것 같긴 함 그래도 일단 긁어옴
    private final  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("데이터베이스에서 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getAuthority().toString());
        return new User(
                String.valueOf(user.getEmail()),
                user.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    // Context에서 현재 로그인한 회원 정보 가져오기
    public static String getCurUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
