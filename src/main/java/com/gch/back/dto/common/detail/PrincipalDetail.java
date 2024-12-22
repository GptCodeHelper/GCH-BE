package com.gch.back.dto.common.detail;

import com.gch.back.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PrincipalDetail implements UserDetails, OAuth2User {

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    // 파마리터에 권한이 없을 경우 -> ROLE_USER 권한으로 입력
    public PrincipalDetail(User user, Map<String, Object> attributes) {
        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        this.user = user;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    // 일반 로그인 시
    public PrincipalDetail(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    // 소셜 로그인 시
    public PrincipalDetail(User user, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes) {
        this.user = user;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    // info 에 들어가는 것들이 토큰에 들어가는 데이터
    public Map<String, Object> getUserInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", user.getUserNm());
        info.put("email", user.getUserId());
        return info;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}