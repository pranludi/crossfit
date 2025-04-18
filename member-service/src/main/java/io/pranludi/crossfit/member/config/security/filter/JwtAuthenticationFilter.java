package io.pranludi.crossfit.member.config.security.filter;

import io.pranludi.crossfit.member.config.security.common.TokenExtractor;
import io.pranludi.crossfit.member.config.security.service.JwtAuthenticationService;
import io.pranludi.crossfit.member.exception.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationService jwtAuthenticationService;
    private final TokenExtractor tokenExtractor;

    public JwtAuthenticationFilter(JwtAuthenticationService jwtAuthenticationService, TokenExtractor tokenExtractor) {
        this.jwtAuthenticationService = jwtAuthenticationService;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        // 만약 서버 간 통신용 헤더가 있으면 JWT 필터를 건너뜁니다.
        if (request.getHeader("Server-Authorization") != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        try {
            tokenExtractor.extractFromHeader(authHeader)
                .ifPresent(token -> {
                    Authentication authentication = jwtAuthenticationService.authenticateToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
        }
    }

}

