package io.pranludi.crossfit.member.config.security.service;

import io.pranludi.crossfit.member.config.security.common.JwtUtil;
import io.pranludi.crossfit.member.config.security.token.AuthenticationToken;
import io.pranludi.crossfit.member.exception.JwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationService {

    final Logger log = LoggerFactory.getLogger(JwtAuthenticationService.class);
    final JwtUtil jwtUtil;
    final UserDetailsService userDetailsService;

    public JwtAuthenticationService(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticateToken(String token) {
        try {
            log.info("JWT 인증 시작 - 토큰: {}", token);

            // 토큰 유효성 검사
            if (!jwtUtil.isTokenValid(token)) {
                log.warn("JWT 토큰이 유효하지 않음");
                throw new JwtAuthenticationException("유효하지 않은 JWT 토큰");
            }

            // 사용자 이름 추출
            String username = jwtUtil.extractUsername(token);
            log.info("JWT에서 추출한 사용자 이름: {}", username);

            // 사용자 정보 로드
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            log.info("사용자 정보 로드 완료 - 권한: {}", userDetails.getAuthorities());

            return new AuthenticationToken(
                userDetails,
                token,
                userDetails.getAuthorities()
            );
        } catch (JwtAuthenticationException e) {
            log.error("JWT 인증 실패: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("JWT 인증 중 알 수 없는 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("JWT 인증 중 오류 발생", e);
        }
    }

}