package com.gch.back.dto.user;
import com.gch.back.entity.BaseEntity;
import com.gch.back.entity.User;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
/**
 * 사용자 정보 수정 시 사용
 * 수정 가능한 필드만 포함
 * null 체크를 통한 부분 업데이트 지원
 */
public class UserUpdateRequestDto extends BaseEntity {
    private String userNm;
    private String userPhoneNo;
    private String userAdd;
    private String userYmd;
    private Integer userScore;
    private String chgId;
    private String userCi;


    // 점수 업데이트
    public User updateScore(User user) {
        return user.withUpdateScore(this.userScore, this.chgId);
    }

    // Entity 업데이트 메서드
    public User updateAuthInfo(User user) {
        return user.withUpdateAuthInfo(this.userCi, this.userYmd, this.userNm, this.userPhoneNo, this.chgId);
    }
}
