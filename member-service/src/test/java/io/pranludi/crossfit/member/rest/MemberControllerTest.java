package io.pranludi.crossfit.member.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pranludi.crossfit.member.config.security.filter.JwtAuthenticationFilter;
import io.pranludi.crossfit.member.config.security.filter.ServerAuthenticationFilter;
import io.pranludi.crossfit.member.domain.EnvironmentData;
import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.domain.MemberGrade;
import io.pranludi.crossfit.member.service.MakeEnvironment;
import io.pranludi.crossfit.member.service.MemberService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = MemberController.class)
@WebAppConfiguration
class MemberControllerTest {

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
    void 회원_조회() throws Exception {
        // given
        MemberEntity memberEntity = new MemberEntity("test01", "test_password", "branchId", "name", "email", "phoneNumber", MemberGrade.NORMAL, LocalDateTime.now());
        given(memberService.findById()).willReturn((EnvironmentData e) -> memberEntity);

        // when
        var result = mvc.perform(
                get("/api/v1/member/test01")
                    .with(SecurityMockMvcRequestPostProcessors.user("test"))
            )
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();

        // then
        var resultMemberEntity = objectMapper.readValue(result.getResponse().getContentAsString(), MemberEntity.class);
        Assertions.assertEquals(memberEntity.id(), resultMemberEntity.id());
    }
}