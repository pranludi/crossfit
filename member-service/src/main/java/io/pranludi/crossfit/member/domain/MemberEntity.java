package io.pranludi.crossfit.member.domain;

import java.time.LocalDateTime;

public record MemberEntity(
    String id,
    String password,
    String name,
    String email,
    String phoneNumber,
    MemberGrade grade,
    LocalDateTime lastPaidAt
) {

    public static MemberEntity of(String id, String password, String name, String email, String phoneNumber) {
        return new MemberEntity(
            id,
            password,
            name,
            email,
            phoneNumber,
            MemberGrade.NORMAL,
            LocalDateTime.of(1970, 1, 1, 0, 0, 0)
        );
    }
}
