package com.gch.back.service;

import com.gch.back.dto.UserRole;
import com.gch.back.dto.common.detail.PrincipalDetail;
import com.gch.back.dto.oauth.KakaoUserInfo;
import com.gch.back.entity.User;
import com.gch.back.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // TODO: DB Inser Logic 추가
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(attributes);
        String socialId = kakaoUserInfo.getSocialId();
        String name = String.valueOf(kakaoUserInfo.getProfile().get("nickname"));

        Optional<User> userOptional = userRepository.findByUserId(socialId);
        User user = userOptional.orElseGet(() -> userRepository.save(
                User.builder()
                        .userId(socialId)
                        .userNm(name)
                        .userRole(UserRole.USER)
                        .build()
                )
        );

        return new PrincipalDetail(user, attributes);
    }
}
