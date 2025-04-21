package io.pranludi.crossfit.member.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pranludi.crossfit.member.config.TestSecurityConfig;
import io.pranludi.crossfit.member.config.security.filter.JwtAuthenticationFilter;
import io.pranludi.crossfit.member.config.security.filter.ServerAuthenticationFilter;
import io.pranludi.crossfit.member.domain.EnvironmentData;
import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.rest.dto.MemberResponse;
import io.pranludi.crossfit.member.rest.dto.MemberSaveRequest;
import io.pranludi.crossfit.member.rest.mapper.MemberSaveRequestMapper;
import io.pranludi.crossfit.member.service.MakeEnvironment;
import io.pranludi.crossfit.member.service.MemberService;
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

// 회원 등록은 MemberController 에 있지만, spring security 의 인증을 하지 않도록 해야 한다.
@Import(TestSecurityConfig.class)
@WebMvcTest(controllers = MemberController.class)
@WebAppConfiguration
class MemberControllerAddTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @MockitoBean
    MemberService memberService;
    @MockitoBean
    MakeEnvironment makeEnvironment;
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
    void 회원_등록() throws Exception {
        // given
        var req = new MemberSaveRequest("test01", "test_password", "branchId", "name", "email", "phoneNumber");
        var content = objectMapper.writeValueAsString(req);
        MemberEntity memberEntity = MemberSaveRequestMapper.INSTANCE.toEntity(req);
        given(memberService.save(memberEntity)).willReturn((EnvironmentData e) -> memberEntity);

        // when
        var result = mvc.perform(
                post("/api/v1/member/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content)
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();

        // then
        var resultRes = objectMapper.readValue(result.getResponse().getContentAsString(), MemberResponse.class);
        Assertions.assertEquals(memberEntity.id(), resultRes.id());
    }

}