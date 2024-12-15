package com.gch.back.controller;

import com.gch.back.common.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class OauthController {
    @PostMapping("kakao")
    public ResponseUtil<?> kakaoLogin() {
        return ResponseUtil.successResponse("a");
    }
}
