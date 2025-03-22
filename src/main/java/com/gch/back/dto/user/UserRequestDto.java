package com.gch.back.dto.user;
import com.gch.back.entity.User;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 회원가입 시 사용
 * 필수 필드에 대한 유효성 검사 (@NotBlank)
 * Entity 변환 메서드 포함
 */
public class UserRequestDto {
    @NotBlank(message = "아이디는 필수 입력값입니다")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    private String testInput;

    @NotBlank(message = "이름은 필수 입력값입니다")
    private String userNm;

    private Integer userScore;
    private String userPhoneNo;
    private String userAdd;
    private String userYmd;
    private String userCi;

    // Entity 변환 메서드
    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .testInput(this.testInput)
                .userNm(this.userNm)
                .userScore(this.userScore)
                .userPhoneNo(this.userPhoneNo)
                .userAdd(this.userAdd)
                .userYmd(this.userYmd)
                .userCi(this.userCi)
                .build();
    }
}