package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.service.AuthService;
import com.haruhanjan.authentication.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(CustomUserLoginController.class)
@AutoConfigureRestDocs
class CustomUserLoginControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private AuthService authService;
    @Autowired
    private MockMvc mockMvc; // http 호출
    @Test
    void getAll() {
    }

    @Test
    void join() {
    }

    @Test
    void login() {
    }

    @Test
    void delete() {
    }
}