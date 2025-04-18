package io.pranludi.crossfit.branch.rest.mapper;

import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.rest.dto.BranchSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchSaveRequestMapper {

    BranchSaveRequestMapper INSTANCE = Mappers.getMapper(BranchSaveRequestMapper.class);

    default BranchEntity toEntity(BranchSaveRequest dto) {
        return new BranchEntity(
            dto.id(),
            dto.password(),
            dto.name(),
            dto.email(),
            dto.phoneNumber(),
            0
        );
    }

}
