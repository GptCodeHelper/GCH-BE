package com.gch.back.repository;

import com.gch.back.dto.user.UserRequestDto;
import com.gch.back.dto.user.UserResponseDto;
import com.gch.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String email);
}
