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
public class CustomUserDetailsService {
    // Context에서 현재 로그인한 회원 정보 가져오기
    public static String getCurUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
