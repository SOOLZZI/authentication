package com.haruhanjan.authentication.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ SpringExtension.class})
@WebMvcTest(CustomUserLoginController.class)
@AutoConfigureRestDocs
class CustomUserLoginControllerTest {

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