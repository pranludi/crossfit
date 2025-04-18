package io.pranludi.crossfit.branch.rest;

import io.pranludi.crossfit.branch.domain.BranchEntity;
import io.pranludi.crossfit.branch.rest.dto.BranchResponse;
import io.pranludi.crossfit.branch.rest.dto.BranchSaveRequest;
import io.pranludi.crossfit.branch.rest.mapper.BranchResponseMapper;
import io.pranludi.crossfit.branch.rest.mapper.BranchSaveRequestMapper;
import io.pranludi.crossfit.branch.service.BranchService;
import io.pranludi.crossfit.branch.service.MakeEnvironment;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/")
    public ResponseEntity<BranchResponse> save(@RequestBody BranchSaveRequest dto) {
        BranchEntity branchEntity = BranchSaveRequestMapper.INSTANCE.toEntity(dto);
        var saved = branchService.save(branchEntity).apply(makeEnvironment.make(branchEntity.id()));
        return ResponseEntity.ok(BranchResponseMapper.INSTANCE.toEntity(saved));
    }

    @GetMapping("/{branch-id}")
    public ResponseEntity<BranchResponse> findById(@PathVariable("branch-id") String branchId) {
        BranchEntity branch = branchService.findById().apply(makeEnvironment.make(branchId));
        return ResponseEntity.ok(BranchResponseMapper.INSTANCE.toEntity(branch));
    }

    @GetMapping("/all-branches")
    public ResponseEntity<List<BranchResponse>> findAll() {
        List<BranchEntity> branches = branchService.findAll().apply(makeEnvironment.make("id"));
        var result = branches.stream().map(BranchResponseMapper.INSTANCE::toEntity).toList();
        return ResponseEntity.ok(result);
    }
}
