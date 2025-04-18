package io.pranludi.crossfit.member.config.security.service;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.service.MakeEnvironment;
import io.pranludi.crossfit.member.service.MemberService;
import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final MemberService memberService;
    final MakeEnvironment makeEnvironment;

    public CustomUserDetailsService(MemberService memberService, MakeEnvironment makeEnvironment) {
        this.memberService = memberService;
        this.makeEnvironment = makeEnvironment;
    }

    // 사용자 이름을 기반으로 사용자 정보를 조회합니다.
    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberEntity memberEntity = memberService.findById().apply(makeEnvironment.make(username));
        return createUserDetails(memberEntity);
    }

    private UserDetails createUserDetails(MemberEntity member) {
        return new User(
            member.id(),
            member.password(),
            Set.of(new SimpleGrantedAuthority("ROLE_USER")),
            true
        );
    }

}