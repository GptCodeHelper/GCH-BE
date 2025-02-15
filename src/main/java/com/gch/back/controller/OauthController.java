package com.gch.back.controller;

import com.gch.back.common.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
