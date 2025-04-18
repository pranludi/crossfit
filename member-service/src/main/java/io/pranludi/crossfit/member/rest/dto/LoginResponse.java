package io.pranludi.crossfit.member.rest.dto;

public record LoginResponse(
    String accessToken,
    String tokenType
) {

    public static LoginResponse of(String token, String bearer) {
        return new LoginResponse(token, bearer);
    }
}
