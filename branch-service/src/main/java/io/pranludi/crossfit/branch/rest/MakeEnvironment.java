package io.pranludi.crossfit.branch.rest;

import io.pranludi.crossfit.branch.domain.EnvironmentData;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MakeEnvironment {

    public EnvironmentData make(String branchId) {
        EnvironmentData environmentData = new EnvironmentData(branchId, LocalDateTime.now());
        return environmentData;
    }
}
