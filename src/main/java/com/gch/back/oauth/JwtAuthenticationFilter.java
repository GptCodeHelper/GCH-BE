package com.gch.back.oauth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider tokenProvider;

    // 보호가 필요 없는 URL 리스트
    private static final List<String> EXCLUDED_URLS = List.of(
            "/",
            "/login",
            "/v1/oauth",
            "/oauth/login/kakao",
            "/oauth/join",
            "/favicon.ico",
            "/login/oauth2/code/kakao"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        logger.info("--------------------------- JwtVerifyFilter ---------------------------");
        logger.info("request url : " + request.getRequestURI());

        boolean excluded = EXCLUDED_URLS.stream().anyMatch(excludedUrl -> request.getRequestURI().startsWith(excludedUrl));
        if (excluded) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenProvider.getAccessToken(request).orElseThrow();

        if (tokenProvider.validateToken(token)) {
            String email = tokenProvider.getEmailFromJWT(token);
            // 여기서는 권한(roles) 처리 없이 username만 설정합니다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
