package io.pranludi.crossfit.member.rest;

import io.pranludi.crossfit.member.domain.EnvironmentData;
import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.rest.dto.SaveMember;
import io.pranludi.crossfit.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    final MemberService memberService;
    final MakeEnvironment makeEnvironment;

    public MemberController(MemberService memberService, MakeEnvironment makeEnvironment) {
        this.memberService = memberService;
        this.makeEnvironment = makeEnvironment;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
        @RequestHeader("member-id") String headerMemberId,
        @RequestBody SaveMember saveMember
    ) {
        EnvironmentData env = makeEnvironment.make(headerMemberId);
        MemberEntity memberEntity = MemberEntity.of(
            env.id(),
            saveMember.password(),
            saveMember.name(),
            saveMember.email(),
            saveMember.phoneNumber()
        );

        memberService.save(memberEntity).apply(env);
    }

    @GetMapping("/member/{member-id}")
    public ResponseEntity<MemberEntity> findById(
        @RequestHeader("member-id") String headerMemberId,
        @PathVariable("member-id") String memberId
    ) {
        MemberEntity member = memberService.findById(memberId).apply(makeEnvironment.make(headerMemberId));
        return ResponseEntity.ok(member);
    }
}
