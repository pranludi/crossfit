package io.pranludi.crossfit.branch.config.security.service;

import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.service.BranchService;
import io.pranludi.crossfit.branch.service.MakeEnvironment;
import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final BranchService branchService;
    final MakeEnvironment makeEnvironment;

    public CustomUserDetailsService(BranchService branchService, MakeEnvironment makeEnvironment) {
        this.branchService = branchService;
        this.makeEnvironment = makeEnvironment;
    }

    // 사용자 이름을 기반으로 사용자 정보를 조회합니다.
    @Override
    public UserDetails loadUserByUsername(String username) {
        BranchEntity branchEntity = branchService.findById().apply(makeEnvironment.make(username));
        return createUserDetails(branchEntity);
    }

    private UserDetails createUserDetails(BranchEntity branch) {
        return new User(
            branch.id(),
            branch.password(),
            Set.of(new SimpleGrantedAuthority("ROLE_USER")),
            true
        );
    }

}