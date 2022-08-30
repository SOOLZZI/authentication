package com.haruhanjan.authentication.repository;

import com.haruhanjan.authentication.dto.UserAuthResponse;

import java.util.Optional;

public interface RefreshTokenRepository {

   Optional<UserAuthResponse> isExist(String token);
}
