package io.pranludi.crossfit.member.rest.dto;

public record MemberResponse(
    String id,
    String name,
    String branchId,
    String email,
    String phoneNumber
) {

}