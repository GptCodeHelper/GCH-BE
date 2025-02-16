package com.gch.back.oauth;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.gch.back.entity.User;
import com.gch.back.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("---- success oauth login ----");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Kakao의 응답 구조에 따라 attribute key가 다를 수 있음
        // kakao_account = email / properties = nickname
        System.out.println("getAttributes = " + oAuth2User.getAttributes());

        Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");
        String email = (String) kakaoAccount.get("email");
        String name = (String) properties.get("nickname");

        // URL 인코딩 처리
        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);
        String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);

        System.out.println("email : " + email);
        // DB에 해당 email이 존재하는지 확인
        Optional<User> userOptional = userRepository.findByUserId(email);

        if (userOptional.isPresent()) {
            // 기존 회원인 경우 JWT 생성 후 헤더에 추가
            String token = jwtTokenProvider.generateToken(email);
            response.addHeader("Authorization", "Bearer " + token);
            // 메인 페이지로 리다이렉트
            response.sendRedirect("/");
        } else {
            // 신규 사용자: FE에서 추가 정보 입력받을 수 있도록 oauth 정보를 전달
            // 예시: FE의 회원가입 페이지 (/signup)로 email, name을 쿼리 파라미터로 전달
            String redirectUrl = "/signup?email=" + encodedEmail + "&name=" + encodedName;
            response.sendRedirect(redirectUrl);
        }
    }
}
