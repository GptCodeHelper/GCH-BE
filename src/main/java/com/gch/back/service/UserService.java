package com.gch.back.service;

import com.gch.back.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> saveUser(User user);
}
