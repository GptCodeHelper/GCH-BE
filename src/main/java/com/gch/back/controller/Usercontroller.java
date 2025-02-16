package com.gch.back.controller;

import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.entity.User;
import com.gch.back.oauth.JwtTokenProvider;
import com.gch.back.repository.UserRepository;
import com.gch.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class Usercontroller {
    private final UserService userService;

    @GetMapping("/")
    public String mainPageTest() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPageTest() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPageTest() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(UserRequestDto request) {
        return userService.saveUser(request);
    }
}
