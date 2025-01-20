package com.gch.back.dto.user;

import com.gch.back.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 사용자 정보 조회 시 사용
 * 비밀번호 제외
 * Entity에서 DTO로 변환하는 정적 메서드 포함
 */
public class UserResponseDto {
    // 사용자 정보
    private Integer userSeq;
    private String userId;
    private String userNm;
    private Integer userScore;
    private String userPhoneNo;
    private String userAdd;
    private String userYmd;
    private String userCi;

    // 감사 정보
    private LocalDateTime regTime;    // 등록일시
    private String regId;             // 등록자
    private LocalDateTime chgDt;      // 수정일시
    private String chgId;             // 수정자

    // Entity -> DTO 변환 메서드
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userSeq(user.getUserSeq())
                .userId(user.getUserId())
                .userNm(user.getUserNm())
                .userScore(user.getUserScore())
                .userPhoneNo(user.getUserPhoneNo())
                .userAdd(user.getUserAdd())
                .userYmd(user.getUserYmd())
                .userCi(user.getUserCi())
                // 감사 정보 설정
                .regTime(user.getRegTime())
                .regId(user.getRegId())
                .chgDt(user.getChgDt())
                .chgId(user.getChgId())
                .build();
    }
}
