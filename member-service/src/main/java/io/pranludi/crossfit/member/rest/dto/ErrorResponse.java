package io.pranludi.crossfit.member.rest.dto;

public record ErrorResponse(
    int code,
    String message
) {

}
