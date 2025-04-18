package io.pranludi.crossfit.branch.service;

import io.pranludi.crossfit.branch.config.security.common.JwtUtil;
import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.repository.BranchRepository;
import io.pranludi.crossfit.branch.service.mapper.BranchMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    final BranchRepository branchRepository;
    final BranchMapper branchMapper;
    final JwtUtil jwtUtil;

    public AuthService(BranchRepository branchRepository, BranchMapper branchMapper, JwtUtil jwtUtil) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.jwtUtil = jwtUtil;
    }

    // 로그인
    public String login(String branchId, String password) {
        BranchEntity branchEntity = branchRepository.findById(branchId)
            .filter(branch -> branch.getPassword().equals(password))
            .map(branchMapper::toEntity)
            .orElseThrow(() -> new IllegalArgumentException("Invalid id or password"));

        // JWT 토큰 생성
        return jwtUtil.generateToken(branchEntity.id());
    }

}