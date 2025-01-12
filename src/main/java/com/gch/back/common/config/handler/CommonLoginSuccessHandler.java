package com.gch.back.common.config.handler;

import com.gch.back.common.jwt.JwtConstants;
import com.gch.back.common.jwt.JwtUtils;
import com.gch.back.dto.UserRole;
import com.gch.back.dto.oauth.CustomOAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class CommonLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Success OAuth Login!!");

        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            Map<String, Object> userInfo = oAuth2User.getUserInfo();

            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트
            if(oAuth2User.getRole() == UserRole.GUEST) {
                String accessToken = JwtUtils.generateToken(userInfo, JwtConstants.ACCESS_EXP_TIME);
                String refreshToken = JwtUtils.generateToken(userInfo, JwtConstants.REFRESH_EXP_TIME);

                response.addHeader("accessToken", "Bearer " + accessToken);
                response.addHeader("refreshToken", refreshToken);
                response.sendRedirect("oauth2/sign-up"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트
                response.setContentType("application/json; charset=UTF-8");

            } else {
                loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        Map<String, Object> userInfo = oAuth2User.getUserInfo();

        // TODO: refresh Token 설정 수정 필요 / 등록 로직 추가
        String accessToken = JwtUtils.generateToken(userInfo, JwtConstants.ACCESS_EXP_TIME);
        String refreshToken = JwtUtils.generateToken(userInfo, JwtConstants.REFRESH_EXP_TIME);
        response.addHeader("accessToken", "Bearer " + accessToken);
        response.addHeader("refreshToken", "Bearer " + refreshToken);
    }
}
