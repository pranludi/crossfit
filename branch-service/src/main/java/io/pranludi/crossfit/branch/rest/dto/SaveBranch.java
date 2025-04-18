package io.pranludi.crossfit.branch.rest.dto;

public record SaveBranch(
    String password,
    String name,
    String email,
    String phoneNumber
) {

}
