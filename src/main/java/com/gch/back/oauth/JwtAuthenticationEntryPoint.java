package com.gch.back.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.info("------- EntryPoint -------");
        log.info("entrypoing url : " + request.getRequestURI());

        if (request.getRequestURI().equals("/error") || request.getRequestURI().equals("/login")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            // JWT가 없거나 유효하지 않을 경우 로그인 페이지로 리다이렉트
            response.sendRedirect("/v1/oauth/login");
        }
    }
}