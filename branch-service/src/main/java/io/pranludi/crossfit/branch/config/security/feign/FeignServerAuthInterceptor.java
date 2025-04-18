package io.pranludi.crossfit.branch.config.security.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.pranludi.crossfit.branch.config.security.common.ServerTokenUtil;
import io.pranludi.crossfit.branch.config.security.server.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class FeignServerAuthInterceptor implements RequestInterceptor {

    final ServerTokenUtil serverTokenUtil;
    final ServerProperties serverProperties;

    public FeignServerAuthInterceptor(ServerTokenUtil serverTokenUtil, ServerProperties serverProperties) {
        this.serverTokenUtil = serverTokenUtil;
        this.serverProperties = serverProperties;
    }

    @Override
    public void apply(RequestTemplate template) {
        // 서버 토큰 생성
        String token = serverTokenUtil.generateServerToken(
            serverProperties.getType(),
            serverProperties.getId()
        );

        // 토큰을 헤더에 추가
        template.header("Server-Authorization", "Server " + token);
    }

}
