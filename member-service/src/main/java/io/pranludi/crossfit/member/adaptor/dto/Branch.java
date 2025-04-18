package io.pranludi.crossfit.member.adaptor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// branch service 의 BranchDTO 와 계속 동기화 시킬수 없어서 필요한 부분만 json-object 시키고 나머지는 무시하도록 함
@JsonIgnoreProperties(ignoreUnknown = true)
public record Branch(
    String id,
    String password,
    String name,
    String email,
    String phoneNumber
) {

}
