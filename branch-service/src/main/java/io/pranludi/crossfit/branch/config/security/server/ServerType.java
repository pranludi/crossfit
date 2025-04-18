package io.pranludi.crossfit.branch.config.security.server;

public enum ServerType {

    AUTH_SERVICE("MEMBER_SERVICE", "회원 서비스"),
    BRANCH_SERVICE("BRANCH_SERVICE", "지점 서비스"),
    MEMBER_SERVICE("MEMBER_SERVICE", "회원 서비스");

    private final String code;
    private final String description;

    ServerType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
