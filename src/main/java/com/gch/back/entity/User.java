package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_TB")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ")
    private Integer userSeq;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_PW")
    private String testInput;
    @Column(name = "USER_NM")
    private String userNm;
    @Column(name = "USER_SCORE")
    private Integer userScore;
    @Column(name = "USER_PHONE_NO")
    private String userPhoneNo;
    @Column(name = "USER_ADD")
    private String userAdd;
    @Column(name = "USER_YMD")
    private String userYmd;
    @Column(name = "USER_CI")
    private String userCi;

    // 점수 업데이트
    public User withUpdateScore(Integer newScore, String chgId) {
        User updated = this.toBuilder()
                .userScore(newScore)
                .build();
        updated.updateAuditInfo(chgId);
        return updated;
    }

    // 인증정보 업데이트
    public User withUpdateAuthInfo(String userCi, String userYmd, String userNm, String userPhoneNo, String chgId) {
        User updated = this.toBuilder()
                .userCi(userCi)
                .userYmd(userYmd)
                .userNm(userNm)
                .userPhoneNo(userPhoneNo)
                .build();
        updated.updateAuditInfo(chgId);
        return updated;
    }

    // 감사 정보 업데이트
    protected void updateAuditInfo(String chgId) {
        this.updateAuditInfo(userId);
    }
}
