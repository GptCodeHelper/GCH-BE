package com.gch.back.oauth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.access.expiration}")
    private long ACCESS_TOKEN_EXPIRATION; // 30분

    @Value("${jwt.access.header}")
    private String ACCESS_HEADER;

    private static final String TOKEN_START_STRING = "Bearer ";

    private Key getSigningKey() {
        // jwtSecret 문자열을 바이트 배열로 변환하여 일관된 HMAC SHA 키 생성
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Access Token 불러오기
    public Optional<String> getAccessToken(HttpServletRequest request) {
        // 1. 쿠키에서 "accessToken" 찾기
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    return Optional.of(cookie.getValue());
                }
            }
        }
        // 2. 헤더에서 "Authorization" 찾기
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring("Bearer ".length()));
        }
        return Optional.empty();
    }

    public String getEmailFromToken(String accessToken) {
        if (validateToken(accessToken)) {
            return getEmailFromJWT(accessToken);
        }
        return null;
    }

    // Authentication 객체 기반으로 토큰 생성
    public String generateToken(Authentication authentication) {
        log.info("generate Token authentication : " + authentication);

        Object principal = authentication.getPrincipal();
        String username = null;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof OAuth2User) {
            // CustomOAuth2User가 OAuth2User를 구현하고 있다면 getUsername()을 호출
            // 또는 attributes에서 "email" 키를 직접 추출할 수 있습니다.
            if (principal instanceof CustomOAuth2User) {
                username = ((CustomOAuth2User) principal).getUsername();
            } else {
                // fallback: 직접 "email" 속성을 꺼내보기
                username = ((OAuth2User) principal).getAttribute("email");
            }
        }

        log.info("generate Token username: " + username);

        if (username == null) {
            throw new IllegalArgumentException("Username (or unique identifier) is null");
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    // email 기반으로 토큰 생성
    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    public String getEmailFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .setAllowedClockSkewSeconds(60)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException
                 | UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Error" + ex);
            return false;
        }
    }
}

