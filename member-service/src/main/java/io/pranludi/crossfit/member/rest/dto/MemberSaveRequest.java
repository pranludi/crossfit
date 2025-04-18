package io.pranludi.crossfit.member.rest.dto;

public record MemberSaveRequest(
    String id,
    String password,
    String branchId,
    String name,
    String email,
    String phoneNumber
) {

}
