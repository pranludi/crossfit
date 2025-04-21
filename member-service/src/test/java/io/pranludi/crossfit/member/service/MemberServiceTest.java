package io.pranludi.crossfit.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import io.pranludi.crossfit.member.adaptor.BranchClient;
import io.pranludi.crossfit.member.adaptor.MemberProducer;
import io.pranludi.crossfit.member.adaptor.dto.Branch;
import io.pranludi.crossfit.member.domain.EnvironmentData;
import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.domain.MemberGrade;
import io.pranludi.crossfit.member.repository.MemberRepository;
import io.pranludi.crossfit.member.repository.dto.MemberDTO;
import io.pranludi.crossfit.member.service.mapper.MemberMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    MemberProducer memberProducer;
    @Mock
    MemberMapper memberMapper;
    @Mock
    BranchClient branchClient;

    @InjectMocks
    MemberService memberService;

    @Test
    void save() {
        // given
        var env = new EnvironmentData("id", LocalDateTime.now());
        var memberEntity = new MemberEntity("id", "password", "branchId", "name", "email", "phoneNumber", MemberGrade.NORMAL, LocalDateTime.now());
        var branchClientData = ResponseEntity.ok(new Branch("id", "password", "name", "email", "phoneNumber"));
        var memberDTO = new MemberDTO("id", "password", "branchId", "name", "email", "phoneNumber", MemberGrade.NORMAL, LocalDateTime.now());
        given(branchClient.findById(anyString())).willReturn(branchClientData);
        given(memberMapper.toDto(any(MemberEntity.class))).willReturn(memberDTO);
        given(memberRepository.save(any(MemberDTO.class))).willReturn(memberDTO);
        given(memberMapper.toEntity(any(MemberDTO.class))).willReturn(memberEntity);

        // when
        MemberEntity savedMember = memberService.save(memberEntity).apply(env);

        // then
        assertEquals(memberEntity.id(), savedMember.id());
    }

    @Test
    void findById() {
        // given
        var env = new EnvironmentData("id", LocalDateTime.now());
        var memberEntity = new MemberEntity("id", "password", "branchId", "name", "email", "phoneNumber", MemberGrade.NORMAL, LocalDateTime.now());
        var memberDTO = new MemberDTO("id", "password", "branchId", "name", "email", "phoneNumber", MemberGrade.NORMAL, LocalDateTime.now());
        given(memberRepository.findById(anyString())).willReturn(Optional.of(memberDTO));
        given(memberMapper.toEntity(memberDTO)).willReturn(memberEntity);

        // when
        var findMember = memberService.findById().apply(env);

        // then
        assertEquals(env.id(), findMember.id());
    }
}