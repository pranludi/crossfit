package io.pranludi.crossfit.member.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pranludi.crossfit.member.config.TestSecurityConfig;
import io.pranludi.crossfit.member.config.security.common.JwtUtil;
import io.pranludi.crossfit.member.config.security.filter.JwtAuthenticationFilter;
import io.pranludi.crossfit.member.config.security.filter.ServerAuthenticationFilter;
import io.pranludi.crossfit.member.rest.dto.LoginRequest;
import io.pranludi.crossfit.member.rest.dto.LoginResponse;
import io.pranludi.crossfit.member.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Import(TestSecurityConfig.class)
@WebMvcTest(AuthController.class)
@WebAppConfiguration
class AuthControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @MockitoBean
    AuthService authService;
    @MockitoBean
    JwtUtil jwtUtil;
    @MockitoBean
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    ServerAuthenticationFilter serverAuthenticationFilter;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    void 사용자_로그인_토큰_생성() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest("test_id", "test_password");
        String content = objectMapper.writeValueAsString(loginRequest);
        String givenToken = "jwt_token";
        given(authService.login(loginRequest.id(), loginRequest.password())).willReturn(givenToken);

        // when
        var result = mvc.perform(
                post("/api/v1/auth/member/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();

        // then
        LoginResponse loginResponse = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponse.class);
        Assertions.assertEquals(givenToken, loginResponse.accessToken());
    }

}

