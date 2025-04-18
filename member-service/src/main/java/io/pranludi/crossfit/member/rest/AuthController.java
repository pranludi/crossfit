package io.pranludi.crossfit.member.rest;

import io.pranludi.crossfit.member.config.security.common.JwtUtil;
import io.pranludi.crossfit.member.rest.dto.LoginRequest;
import io.pranludi.crossfit.member.rest.dto.LoginResponse;
import io.pranludi.crossfit.member.rest.dto.TokenRefreshRequest;
import io.pranludi.crossfit.member.rest.dto.TokenResponse;
import io.pranludi.crossfit.member.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/member")
public class AuthController {

    final JwtUtil jwtUtil;
    final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.id(), request.password());
        return ResponseEntity.ok(LoginResponse.of(token, "Bearer"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.refreshToken();

        if (jwtUtil.isTokenValid(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            String newAccessToken = jwtUtil.generateToken(username);
            return ResponseEntity.ok(TokenResponse.of(newAccessToken, refreshToken));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}