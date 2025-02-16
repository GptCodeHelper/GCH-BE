package com.gch.back.service;

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
    public ResponseEntity<?> saveUser(User user) {
        // 추가 검증 및 비즈니스 로직 수행 가능 (예: 중복 체크 등)
        User savedUser = userRepository.save(user);

        // 저장 후 JWT 토큰 생성 (자동 로그인 처리)
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getUserId(), null, null);
        String token = jwtTokenProvider.generateToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return ResponseEntity.ok().headers(headers).body("User registered successfully");
    }
}
