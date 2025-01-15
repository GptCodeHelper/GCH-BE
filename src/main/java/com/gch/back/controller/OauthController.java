package com.gch.back.controller;

import com.gch.back.common.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class OauthController {
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect_uri}")
    private String redirectUri;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("kakao")
    public ResponseUtil<?> kakaoLogin() {
        return ResponseUtil.successResponse("a");
    }
}
