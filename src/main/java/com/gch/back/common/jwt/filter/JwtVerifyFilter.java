package com.gch.back.common.jwt.filter;

import com.gch.back.common.PasswordUtil;
import com.gch.back.common.jwt.JwtUtils;
import com.gch.back.entity.User;
import com.gch.back.repository.user.UserRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtVerifyFilter extends OncePerRequestFilter {
    private static final String NO_CHECK_URL = "/login"; // "/login"으로 들어오는 요청은 Filter 작동 X

    private final JwtUtils jwtUtil;
    private final UserRepository userRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        logger.info("--------------------------- JwtVerifyFilter ---------------------------");

        logger.info("request URL : " + request.getRequestURI());
        logger.info("dispatcherType : " + request.getDispatcherType());

        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response); // "/login" 요청이 들어오면, 다음 필터 호출
            return; // return으로 이후 현재 필터 진행 막기 (안해주면 아래로 내려가서 계속 필터 진행시킴)
        }

        // accessToken을 보냈는지 확인 -> AccessToken이 만료되어씀 의미, null일경우 패스
        String accessToken = jwtUtil.extractAccessToken(request)
                .filter(jwtUtil::isTokenValid)
                .orElse(null);

        // AccessToken 검증 및 인증 처리
        if (accessToken == null) {
            checkAccessTokenAndAuthentication(request, response, filterChain);
        }

        // TODO: Refresh Token DB추가 시 변경
        // DB에 저장되어 있는 RefreshToken과 일치하는지 확인
        // RefreshToken을 보낸 경우에는 AccessToken을 재발급 하고 인증 처리는 하지 않게 하기위해 바로 return으로 필터 진행 중지
//        if (accessToken != null) {
//            checkRefreshTokenAndReIssueAccessToken(response, accessToken);
//            return;
//        }
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                  FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication() 호출");

        Optional<String> accessTokenOpt = jwtUtil.extractAccessToken(request).filter(jwtUtil::isTokenValid);

        if (accessTokenOpt.isPresent()) {
            accessTokenOpt.flatMap(jwtUtil::extractEmail)
                    .flatMap(userRepository::findByUserId)
                    .ifPresent(this::saveAuthentication);

            filterChain.doFilter(request, response);
        } else {
            logger.info("JWT 토큰이 존재하지 않습니다. 로그인 페이지로 이동합니다.");
            response.sendRedirect("/login");
            // 또는 OAuth2 로그인 페이지가 있다면 response.sendRedirect("/login/kakao");
            return;
        }
    }

    public void saveAuthentication(User user) {
        String password = user.getPassword();
        if (password == null) { // 소셜 로그인 유저의 비밀번호 임의로 설정 하여 소셜 로그인 유저도 인증 되도록 설정
            password = PasswordUtil.generateRandomPassword();
        }

        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(password)
                .roles(user.getUserRole().name())
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // TODO: Refresh Token DB 추가 시 사용
    // AccessToken / RefreshToken 재발급 및 AccessToken 헤더로 전송
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        userRepository.findByRefreshToken(refreshToken)
                .ifPresent(user -> {
                    String reIssuedRefreshToken = reIssueRefreshToken(user);
                    jwtUtil.sendAccessAndRefreshToken(response, jwtUtil.createAccessToken(user.getUserId(), user.getUserRole()),
                            reIssuedRefreshToken);
                });
    }

    // TODO: Refresh Token DB 추가 시 사용
    // RefreshToken 재발급 및 DB수정
    private String reIssueRefreshToken(User user) {
        String reIssuedRefreshToken = jwtUtil.createRefreshToken();
        user.updateRefreshToken(reIssuedRefreshToken);
        userRepository.saveAndFlush(user);
        return reIssuedRefreshToken;
    }
}