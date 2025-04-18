package io.pranludi.crossfit.branch.rest.mapper;

import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.mapper.EntityMapper;
import io.pranludi.crossfit.branch.rest.dto.BranchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchResponseMapper extends EntityMapper<BranchEntity, BranchResponse> {

    BranchResponseMapper INSTANCE = Mappers.getMapper(BranchResponseMapper.class);

}
