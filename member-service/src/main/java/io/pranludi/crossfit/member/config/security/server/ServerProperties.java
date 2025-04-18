package io.pranludi.crossfit.member.config.security.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ServerProperties {

    private final ServerType type;
    private final String id;

    public ServerProperties(ServerType type, String id) {
        this.type = type;
        this.id = id;
    }

    public ServerType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
