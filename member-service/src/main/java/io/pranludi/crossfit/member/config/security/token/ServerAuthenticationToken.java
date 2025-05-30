package io.pranludi.crossfit.member.config.security.token;

import io.pranludi.crossfit.member.config.security.server.ServerTokenClaims;
import java.util.Collections;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ServerAuthenticationToken extends AbstractAuthenticationToken {

    final String token;
    final ServerTokenClaims serverTokenClaims;

    public ServerAuthenticationToken(ServerTokenClaims serverTokenClaims) {
        super(Collections.singleton(new SimpleGrantedAuthority("ROLE_SERVER")));
        this.token = null; // 토큰 자체는 더 이상 저장할 필요가 없음
        this.serverTokenClaims = serverTokenClaims;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null; // 서버 인증에서는 credentials이 필요없으므로 null 반환
    }

    @Override
    public Object getPrincipal() {
        return serverTokenClaims; // principal로 claims 객체 반환
    }

}
