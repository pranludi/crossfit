package io.pranludi.crossfit.member.rest;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.rest.dto.MemberResponse;
import io.pranludi.crossfit.member.rest.dto.MemberSaveRequest;
import io.pranludi.crossfit.member.rest.mapper.MemberResponseMapper;
import io.pranludi.crossfit.member.rest.mapper.MemberSaveRequestMapper;
import io.pranludi.crossfit.member.service.MakeEnvironment;
import io.pranludi.crossfit.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/")
    public ResponseEntity<MemberResponse> save(@RequestBody MemberSaveRequest dto) {
        MemberEntity memberEntity = MemberSaveRequestMapper.INSTANCE.toEntity(dto);
        var saved = memberService.save(memberEntity).apply(makeEnvironment.make(memberEntity.id()));
        return ResponseEntity.ok(MemberResponseMapper.INSTANCE.toEntity(saved));
    }

    @GetMapping("/{member-id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable("member-id") String memberId) {
        MemberEntity member = memberService.findById().apply(makeEnvironment.make(memberId));
        return ResponseEntity.ok(MemberResponseMapper.INSTANCE.toEntity(member));
    }
}
