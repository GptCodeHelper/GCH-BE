package com.gch.back.config;

import com.gch.back.oauth.*;
import com.gch.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class securityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomOAuth2FailureHandler customOAuth2FailureHandler;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Stateless Session 사용 (JWT 기반 인증)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // CSRF 비활성화 (필요에 따라 활성화 가능)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                    // 메인, oauth login, 회원가입 엔드포인트는 permit
                    .requestMatchers("/",
                            "/v1/oauth/login",
                            "/login/oauth2/code/kakao",
                            "/v1/oauth/login/kakao",
                            "/v1/oauth/join",
                            "/oauth/login/kakao",
                            "/error")
                    .permitAll()
                    // 그 외 요청은 인증 필요
                    .anyRequest().authenticated()
            )
            // JWT 인증 실패 시, 로그인 페이지로 리다이렉트
            .exceptionHandling(exception -> exception
                    .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
            )
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/v1/oauth/login")
                    .userInfoEndpoint(userInfo -> userInfo
                            .userService(customOAuth2UserService)
                    )
                    .failureHandler(customOAuth2FailureHandler)
                    .successHandler(customOAuth2SuccessHandler)
            );

        // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**");
    }
}
