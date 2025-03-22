package com.gch.back.oauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    /**
     * getName()는 Spring Security 내부에서 사용자 식별자로 사용됩니다.
     * 여기서는 getUsername() 메서드를 통해 Kakao 응답에서 email을 추출하여 반환합니다.
     */
    @Override
    public String getName() {
        return getUsername();
    }

    /**
     * Kakao의 응답에서는 email 정보가 "kakao_account" 내부의 "email" 키에 존재합니다.
     * 만약 해당 정보가 없으면, 최상위 "email" 속성을 fallback으로 시도합니다.
     */
    public String getUsername() {
        Map<String, Object> attributes = oauth2User.getAttributes();
        if (attributes.containsKey("kakao_account")) {
            Object kakaoAccountObj = attributes.get("kakao_account");
            if (kakaoAccountObj instanceof Map) {
                Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoAccountObj;
                Object email = kakaoAccount.get("email");
                if (email != null) {
                    return email.toString();
                }
            }
        }
        // Fallback: 최상위 "email" 속성 사용
        Object email = attributes.get("email");
        return email != null ? email.toString() : null;
    }
}