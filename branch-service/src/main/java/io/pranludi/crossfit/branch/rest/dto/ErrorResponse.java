package io.pranludi.crossfit.branch.rest.dto;

public record ErrorResponse(
    int code,
    String message
) {

}
