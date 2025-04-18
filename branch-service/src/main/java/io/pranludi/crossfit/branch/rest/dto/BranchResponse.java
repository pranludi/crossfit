package io.pranludi.crossfit.branch.rest.dto;

public record BranchResponse(
    String id,
    String name,
    String email,
    String phoneNumber,
    int memberCount
) {

}