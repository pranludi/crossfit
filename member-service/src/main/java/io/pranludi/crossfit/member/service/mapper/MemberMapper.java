package io.pranludi.crossfit.member.service.mapper;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.mapper.EntityMapper;
import io.pranludi.crossfit.member.repository.dto.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper extends EntityMapper<MemberDTO, MemberEntity> {

}
