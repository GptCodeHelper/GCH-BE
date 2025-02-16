package com.gch.back.oauth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${jwt.access.expiration}")
    private long ACCESS_TOKEN_EXPIRATION; // 30분

    @Value("${jwt.access.header}")
    private String ACCESS_HEADER;

    private static final String TOKEN_START_STRING = "Bearer ";

    // Access Token 불러오기
    public Optional<String> getAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(TOKEN_START_STRING))
                .map(refreshToken -> refreshToken.replace(TOKEN_START_STRING, ""));
    }

    public String getEmailFromToken(String accessToken) {
        if (validateToken(accessToken)) {
            return getEmailFromJWT(accessToken);
        }
        return null;
    }

    // Authentication 객체 기반으로 토큰 생성
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION);
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
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
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getEmailFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException
                 | UnsupportedJwtException | IllegalArgumentException ex) {
            // 로깅 처리 가능
        }
        return false;
    }
}

