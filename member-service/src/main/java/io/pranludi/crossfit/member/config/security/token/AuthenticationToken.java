package io.pranludi.crossfit.member.config.security.token;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AuthenticationToken(
        Object principal,
        Object credentials,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(principal, credentials, authorities);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        // 수동 호출 방지
        throw new IllegalArgumentException("setAuthenticated() 호출 금지 - 생성자를 통해 설정하십시오.");
    }

}
