package com.gch.back.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        // Kakao에서 전달한 사용자 정보를 로드합니다.
        OAuth2User user = super.loadUser(oAuth2UserRequest);
        // 필요에 따라 사용자 attribute 매핑 작업을 추가할 수 있습니다.
        return user;
    }
}