package io.pranludi.crossfit.branch.service;

import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.domain.EnvironmentData;
import io.pranludi.crossfit.branch.exception.ServerError;
import io.pranludi.crossfit.branch.repository.BranchRepository;
import io.pranludi.crossfit.branch.repository.dto.BranchDTO;
import io.pranludi.crossfit.branch.service.mapper.BranchMapper;
import java.util.List;
import java.util.function.Function;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BranchService {

    final BranchRepository branchRepository;
    final BranchMapper branchMapper;

    public BranchService(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    // 지점 등록
    @Transactional
    public Function<EnvironmentData, BranchEntity> save(BranchEntity branchEntity) {
        return (EnvironmentData env) -> {
            BranchDTO branchDTO = branchMapper.toDto(branchEntity);
            var saved = branchRepository.save(branchDTO);
            return branchMapper.toEntity(saved);
        };
    }

    // 지점 조회
    public Function<EnvironmentData, BranchEntity> findById() {
        return (EnvironmentData env) -> {
            BranchDTO branchDTO = branchRepository.findById(env.id())
                .orElseThrow(() -> ServerError.BRANCH_NOT_FOUND(env.id()));
            return branchMapper.toEntity(branchDTO);
        };
    }

    // 모든 지점
    public Function<EnvironmentData, List<BranchEntity>> findAll() {
        return (EnvironmentData env) -> {
            List<BranchDTO> branchDTOs = branchRepository.findAll();
            return branchMapper.toEntity(branchDTOs);
        };
    }

    // 지점 회원 추가
    @Transactional
    public void updateMemberCount(String branchId) {
        // todo 동시성 오류 발생 가능이 있음
        //   redisson 을 사용한 분산 락이 필요함
        BranchDTO branchDTO = branchRepository.findById(branchId)
            .orElseThrow(() -> ServerError.BRANCH_NOT_FOUND(branchId));
        branchDTO.setMemberCount(branchDTO.getMemberCount() + 1);
        branchRepository.save(branchDTO);
    }
}
