package io.pranludi.crossfit.member.service.mapper;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.mapper.EntityMapper;
import io.pranludi.crossfit.member.repository.dto.MemberDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface MemberMapper extends EntityMapper<MemberDTO, MemberEntity> {

}
