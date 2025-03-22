package com.gch.back.service;

import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.dto.user.UserResponseDto;
import com.gch.back.entity.User;
import com.gch.back.oauth.CustomOAuth2User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserRequestDto request, HttpServletResponse response);

    UserResponseDto retrieveUserInfo(@AuthenticationPrincipal String email);
}
