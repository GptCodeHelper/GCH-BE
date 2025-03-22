package com.gch.back.service;

import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.dto.user.UserResponseDto;
import com.gch.back.entity.User;
import com.gch.back.oauth.JwtTokenProvider;
import com.gch.back.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserRequestDto request, HttpServletResponse response) {

        User savedUser = userRepository.save(
                User.builder()
                        .userId(request.getUserId())
                        .userNm(request.getUserNm())
                        .testInput(passwordEncoder.encode(request.getTestInput()))
                        .userPhoneNo(request.getUserPhoneNo())
                        .build()
        );

        String token = jwtTokenProvider.generateToken(savedUser.getUserId());

        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public UserResponseDto retrieveUserInfo(@AuthenticationPrincipal String email) {
        User userRst = userRepository.findByUserId(email).orElseThrow();

        return UserResponseDto.builder()
                .userId(userRst.getUserId())
                .userNm(userRst.getUserNm())
                .build();
    }
}
