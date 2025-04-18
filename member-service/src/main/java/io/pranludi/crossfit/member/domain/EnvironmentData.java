package io.pranludi.crossfit.member.domain;

import java.time.LocalDateTime;

public record EnvironmentData(
    String id,
    LocalDateTime currentDateTime
) {

}
