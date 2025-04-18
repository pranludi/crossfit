package io.pranludi.crossfit.member.service;

import io.pranludi.crossfit.member.adaptor.BranchClient;
import io.pranludi.crossfit.member.adaptor.MemberProducer;
import io.pranludi.crossfit.member.adaptor.dto.Branch;
import io.pranludi.crossfit.member.domain.EnvironmentData;
import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.exception.ServerError;
import io.pranludi.crossfit.member.repository.MemberRepository;
import io.pranludi.crossfit.member.repository.dto.MemberDTO;
import io.pranludi.crossfit.member.service.mapper.MemberMapper;
import java.util.function.Function;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    final MemberRepository memberRepository;
    final MemberProducer memberProducer;
    final MemberMapper memberMapper;
    final BranchClient branchClient;

    public MemberService(MemberRepository memberRepository, MemberProducer memberProducer, MemberMapper memberMapper, BranchClient branchClient) {
        this.memberRepository = memberRepository;
        this.memberProducer = memberProducer;
        this.memberMapper = memberMapper;
        this.branchClient = branchClient;
    }

    // 회원 등록
    @Transactional
    public Function<EnvironmentData, MemberEntity> save(MemberEntity memberEntity) {
        return (EnvironmentData env) -> {
            ResponseEntity<Branch> branchRes = branchClient.findById(memberEntity.branchId());
            if (branchRes.getStatusCode().isError()) {
                throw ServerError.BRANCH_SERVICE_ERROR(memberEntity.branchId());
            }
            MemberDTO memberDTO = memberMapper.toDto(memberEntity);
            memberDTO.setNew(true);
            MemberDTO savedMember = memberRepository.save(memberDTO);
            MemberEntity savedMemberEntity = memberMapper.toEntity(savedMember);
            memberProducer.sendNewMember(savedMemberEntity);
            return savedMemberEntity;
        };
    }

    // 회원 조회
    public Function<EnvironmentData, MemberEntity> findById() {
        return (EnvironmentData env) -> {
            MemberDTO memberDTO = memberRepository.findById(env.id())
                .orElseThrow(() -> ServerError.MEMBER_NOT_FOUND(env.id()));
            return memberMapper.toEntity(memberDTO);
        };
    }

}
