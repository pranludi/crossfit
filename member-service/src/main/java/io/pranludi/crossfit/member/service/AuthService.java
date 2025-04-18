package io.pranludi.crossfit.member.service;

import io.pranludi.crossfit.member.config.security.common.JwtUtil;
import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.repository.MemberRepository;
import io.pranludi.crossfit.member.service.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    final MemberRepository memberRepository;
    final MemberMapper memberMapper;
    final JwtUtil jwtUtil;

    public AuthService(MemberRepository memberRepository, MemberMapper memberMapper, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.jwtUtil = jwtUtil;
    }

    // 로그인
    public String login(String memberId, String password) {
        // 회원 조회
        MemberEntity memberEntity = memberRepository.findById(memberId)
            .filter(member -> member.getPassword().equals(password))
            .map(memberMapper::toEntity)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id or password"));

        // JWT 토큰 생성
        return jwtUtil.generateToken(memberEntity.id());
    }

}