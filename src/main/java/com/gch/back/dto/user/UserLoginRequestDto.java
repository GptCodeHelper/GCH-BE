package com.gch.back.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 로그인 시 사용
 * ID와 비밀번호만 포함
 * 유효성 검사 포함
 */
public class UserLoginRequestDto {
    @NotBlank(message = "아이디는 필수 입력값입니다")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    private String testInput;
}