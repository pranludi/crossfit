package io.pranludi.crossfit.member.domain.server;

public enum ServerType {

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
