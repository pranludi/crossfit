package io.pranludi.crossfit.branch.config.security.common;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class TokenExtractor {

    private static final String BEARER_PREFIX = "Bearer ";

    public Optional<String> extractFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return Optional.of(authorizationHeader.substring(BEARER_PREFIX.length()));
        }
        return Optional.empty();
    }

}