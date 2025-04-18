package io.pranludi.crossfit.member.rest.dto;

public record SaveMember(
    String password,
    String name,
    String email,
    String phoneNumber
) {

}
