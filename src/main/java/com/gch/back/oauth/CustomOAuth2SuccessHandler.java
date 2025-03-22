package com.gch.back.oauth;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.gch.back.entity.User;
import com.gch.back.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

        CustomOAuth2User customUser = new CustomOAuth2User(oAuth2User);
        String customUsername = customUser.getUsername();
        System.out.println("CustomOAuth2User.getUsername(): " + customUsername);

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                customUser, authentication.getCredentials(), authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        System.out.println("authentication : " + authentication);

        if (userOptional.isPresent()) {
            String token = jwtTokenProvider.generateToken(email);
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("http://localhost:3000/");
        } else {
            String redirectUrl = "http://localhost:3000/signup?email=" + encodedEmail + "&name=" + encodedName;
            response.sendRedirect(redirectUrl);
        }
    }
}
