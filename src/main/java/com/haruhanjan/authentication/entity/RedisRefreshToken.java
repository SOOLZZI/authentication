package com.haruhanjan.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 2592000)
@AllArgsConstructor
public class RedisRefreshToken {

    @Id
    private Long id;

    private String refreshToken;
    //private LocalDateTime localDateTime;

}
