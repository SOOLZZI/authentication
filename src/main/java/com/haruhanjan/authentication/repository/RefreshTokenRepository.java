package com.haruhanjan.authentication.repository;

import com.haruhanjan.authentication.entity.RedisRefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RedisRefreshToken, Long> {

   Optional<RedisRefreshToken> findByRefreshToken(String refreshToken);
   void deleteByRefreshToken(String refreshToken);
}
