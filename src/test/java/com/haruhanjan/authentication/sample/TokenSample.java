package com.haruhanjan.authentication.sample;

import com.haruhanjan.authentication.dto.UserAuthResponse;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.entity.AuthProvider;
import com.haruhanjan.authentication.entity.User;

import java.util.Random;

public class TokenSample {

    Random random = new Random();

    public String getToken() {
        return "yJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwibmlja25hbWUiOiJhYWFhIiwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTY2MzkyNDg5NH0.gl-_9Fqe_YBn0hIGLlAqqVo-hx_JpSPaVHeV7J32gjpG2dv0zno55IHESHPfq5i16mVs22D0HZOkUzDwz_92Vw";
    }

    public User getUser() {
        User user = new User("계정명", "이메일", "비밀번호", "이름", "닉네임", random.nextInt(99), AuthProvider.KAKAO);
        user.setId(random.nextLong());

        return user;
    }

    public UserAuthResponse getUserAuthResponse() {
        return new UserAuthResponse(getUser());
    }

    public UserResponseDto getUserResponseDto() { return  new UserResponseDto(getUser());}
}
