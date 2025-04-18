package io.pranludi.crossfit.member.rest;

import io.pranludi.crossfit.member.domain.EnvironmentData;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MakeEnvironment {

    public EnvironmentData make(String memberId) {
        EnvironmentData environmentData = new EnvironmentData(memberId, LocalDateTime.now());

        return environmentData;
    }
}
