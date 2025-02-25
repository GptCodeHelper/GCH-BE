package com.gch.back.controller;

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

    @GetMapping("/")
    public String mainPageTest() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPageTest() {
        return "login";
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/signup")
    public String signUpPageTest() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@ModelAttribute UserRequestDto request, HttpServletResponse response) {
        return userService.saveUser(request, response);
    }
}
