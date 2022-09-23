package com.haruhanjan.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruhanjan.authentication.apiDocs.RestDocsConfiguration;
import com.haruhanjan.authentication.dto.UserResponseDto;
import com.haruhanjan.authentication.sample.TokenSample;
import com.haruhanjan.authentication.service.AuthService;
import com.haruhanjan.authentication.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomUserLoginController.class)
@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
class CustomUserLoginControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    TokenSample tokenSample = new TokenSample();


    @Test
    @DisplayName("유저 전체 조회")
    void getAllTest() throws Exception {
        //given
        List<UserResponseDto> list = new ArrayList<>();
        list.add(tokenSample.getUserResponseDto());
        list.add(tokenSample.getUserResponseDto());
        list.add(tokenSample.getUserResponseDto());

        given(userService.getAll()).willReturn(list);
        //when

        ResultActions result = mockMvc.perform(get("/api/user")
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andDo(document("get-user-list",
                        responseFields(
                                //fieldWithPath("[].")
                        )
                        ));


    }
}