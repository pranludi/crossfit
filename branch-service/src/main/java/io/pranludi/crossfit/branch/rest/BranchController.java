package io.pranludi.crossfit.branch.rest;

import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.rest.dto.SaveBranch;
import io.pranludi.crossfit.branch.service.BranchService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/branch")
public class BranchController {

    final BranchService branchService;
    final MakeEnvironment makeEnvironment;

    public BranchController(BranchService branchService, MakeEnvironment makeEnvironment) {
        this.branchService = branchService;
        this.makeEnvironment = makeEnvironment;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
        @RequestHeader("branch-id") String headerBranchId,
        @RequestBody SaveBranch saveBranch
    ) {
        BranchEntity branchEntity = new BranchEntity(
            "id",
            saveBranch.password(),
            saveBranch.name(),
            saveBranch.email(),
            saveBranch.phoneNumber()
        );
        branchService.save(branchEntity).apply(makeEnvironment.make(headerBranchId));
    }

    @GetMapping("/branch/{branch-id}")
    public ResponseEntity<BranchEntity> findById(
        @RequestHeader("branch-id") String headerBranchId,
        @PathVariable("branch-id") String branchId
    ) {
        BranchEntity branch = branchService.findById(branchId).apply(makeEnvironment.make(headerBranchId));
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/all-branches")
    public ResponseEntity<List<BranchEntity>> findAll(
        @RequestHeader("branch-id") String headerBranchId
    ) {
        List<BranchEntity> branches = branchService.findAll().apply(makeEnvironment.make(headerBranchId));
        return ResponseEntity.ok(branches);
    }
}
