package com.gch.back.service;

import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.entity.User;
import com.gch.back.oauth.JwtTokenProvider;
import com.gch.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public ResponseEntity<?> saveUser(UserRequestDto request) {

        User savedUser = userRepository.save(
                User.builder()
                        .userId(request.getUserId())
                        .userNm(request.getUserNm())
                        .testInput(request.getTestInput())
                        .userPhoneNo(request.getUserPhoneNo())
                        .build()
        );

        String token = jwtTokenProvider.generateToken(savedUser.getUserId());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return ResponseEntity.ok().headers(headers).body("User registered successfully");
    }
}
