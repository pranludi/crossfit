package io.pranludi.crossfit.member.adaptor.dto;

public record Branch(
    String id,
    String password,
    String name,
    String email,
    String phoneNumber
) {

}
