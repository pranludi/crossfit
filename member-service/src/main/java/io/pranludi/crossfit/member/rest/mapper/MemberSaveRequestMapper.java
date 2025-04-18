package io.pranludi.crossfit.member.rest.mapper;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.member.domain.MemberGrade;
import io.pranludi.crossfit.member.rest.dto.MemberSaveRequest;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberSaveRequestMapper {

    MemberSaveRequestMapper INSTANCE = Mappers.getMapper(MemberSaveRequestMapper.class);

    default MemberEntity toEntity(MemberSaveRequest dto) {
        return new MemberEntity(
            dto.id(),
            dto.password(),
            dto.branchId(),
            dto.name(),
            dto.email(),
            dto.phoneNumber(),
            MemberGrade.NORMAL,
            LocalDateTime.of(1970, 1, 1, 0, 0, 0)
        );
    }

}
