package com.gch.back.controller;

import com.gch.back.dto.user.UserResponseDto;
import com.gch.back.oauth.CustomOAuth2User;
import com.gch.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/userinfo")
    public ResponseEntity<?> retrieveUserInfo(@AuthenticationPrincipal String email) {
        UserResponseDto response = userService.retrieveUserInfo(email);

        return ResponseEntity.ok(response);
    }
}
