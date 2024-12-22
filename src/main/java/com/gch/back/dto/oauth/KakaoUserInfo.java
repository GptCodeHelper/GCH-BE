package com.gch.back.dto.oauth;

import lombok.Data;

import java.util.Map;

@Data
public class KakaoUserInfo {
    private String socialId;
    private Map<String, Object> account;
    private Map<String, Object> profile;

    public KakaoUserInfo(Map<String, Object> attributes) {
        socialId = String.valueOf(attributes.get("id"));
        account = (Map<String, Object>) attributes.get("kakao_account");
        profile = (Map<String, Object>) account.get("profile");
    }
}
