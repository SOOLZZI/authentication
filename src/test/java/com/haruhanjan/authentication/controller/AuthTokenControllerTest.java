package com.haruhanjan.authentication.controller;

import com.haruhanjan.authentication.apiDocs.RestDocsConfiguration;
import com.haruhanjan.authentication.sample.TokenSample;
import com.haruhanjan.authentication.service.AuthService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.Cookie;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthTokenController.class)
@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
class AuthTokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    TokenSample tokenSample = new TokenSample();

    @Test
    @WithMockUser
    @DisplayName("로그인 인증이 잘 되는가")
    void authTest() throws Exception {
        //given
        given(authService.validateToken(anyString()))
                .willReturn(tokenSample.getUserAuthResponse());
        String token = tokenSample.getToken();
        Cookie access_token = new Cookie("access_token", token);
        Cookie refresh_token = new Cookie("refresh_token", token);
        //when
        ResultActions result = mockMvc.perform(get("/api/auth")
                .cookie(access_token, refresh_token)

        );
        //then
        result.andExpect(status().isOk())
                .andDo(document("check-access-token",
                            responseFields(
                                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("사용자 식별자 ID"),
                                    fieldWithPath("accountId").type(JsonFieldType.STRING).description("사용자 계정 ID"),
                                    fieldWithPath("nickname").type(JsonFieldType.STRING).description("사용자 닉네임"),
                                    fieldWithPath("role").type(JsonFieldType.STRING).description("사용자 권한")
                            )
                        ));
    }

    @Test
    @WithMockUser
    @DisplayName("로그아웃")
    void logoutTest() throws Exception {
        //given
        String token = tokenSample.getToken();
        Cookie access_token = new Cookie("access_token", token);
        Cookie refresh_token = new Cookie("refresh_token", token);

        //when
        ResultActions result = mockMvc.perform(post("/api/auth/logout")
                .cookie(access_token, refresh_token));
        //then
        result.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(document("logout"));

    }

}