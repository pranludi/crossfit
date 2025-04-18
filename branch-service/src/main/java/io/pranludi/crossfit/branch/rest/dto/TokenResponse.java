package io.pranludi.crossfit.branch.rest.dto;

public record TokenResponse(
    String accessToken,
    String refreshToken,
    String message
) {

    public static TokenResponse of(String accessToken, String refreshToken) {
        return new TokenResponse(
            accessToken, refreshToken, "Token is successfully created"
        );
    }

}
