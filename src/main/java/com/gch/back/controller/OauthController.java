package com.gch.back.controller;

import com.gch.back.common.ResponseUtil;
import com.gch.back.dto.common.ResponseData;
import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class OauthController {
    private static final Logger log = LoggerFactory.getLogger(OauthController.class);
    private final UserService userService;

    @GetMapping("/logout")
    public ResponseEntity<ResponseData> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseUtil.createSuccessData("Log out!");
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseData> registerUser(@ModelAttribute UserRequestDto request, HttpServletResponse response) {
        userService.saveUser(request, response);
        return ResponseUtil.createSuccessData();
    }
}
