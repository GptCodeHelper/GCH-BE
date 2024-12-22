package com.gch.back.service;

import com.gch.back.dto.common.detail.PrincipalDetail;
import com.gch.back.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserId(username)
                .map(user -> new PrincipalDetail(user, Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().getValue()))))
                .orElseThrow(() -> new UsernameNotFoundException("Not Saved User!!!"));

    }
}
