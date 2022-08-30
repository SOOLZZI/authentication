package com.haruhanjan.authentication.repository;

import com.haruhanjan.authentication.dto.UserAuthResponse;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    @Override
    public Optional<UserAuthResponse> isExist(String token) {
        return Optional.empty();
    }
}
