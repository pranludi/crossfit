package io.pranludi.crossfit.branch.exception;

public class ServerAuthenticationException extends RuntimeException {

    final String message;

    public ServerAuthenticationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
