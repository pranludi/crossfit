package io.pranludi.crossfit.member.adaptor;

import io.pranludi.crossfit.member.adaptor.dto.Branch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service", url = "${application.config.member-url}")
public interface BranchClient {

    @GetMapping(value = "/{branch-id}", headers = "branch-id={header-branch-id}")
    ResponseEntity<Branch> findById(@PathVariable("branch-id") String branchId);

}
