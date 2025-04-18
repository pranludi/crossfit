package io.pranludi.crossfit.member.rest.mapper;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.mapper.EntityMapper;
import io.pranludi.crossfit.member.rest.dto.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberResponseMapper extends EntityMapper<MemberEntity, MemberResponse> {

    MemberResponseMapper INSTANCE = Mappers.getMapper(MemberResponseMapper.class);

}
