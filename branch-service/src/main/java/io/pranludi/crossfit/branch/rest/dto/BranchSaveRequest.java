package io.pranludi.crossfit.branch.rest.dto;

public record BranchSaveRequest(
    String id,
    String password,
    String name,
    String email,
    String phoneNumber
) {

}
