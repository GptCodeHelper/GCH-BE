package com.gch.back.dto;

import com.gch.back.entity.BaseEntity;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto extends BaseEntity {
    private Integer userNo;
    private String userId;
    private String testInput;
    private String userNm;
    private Integer userScore;
    private String userPhoneNo;
    private String userAdd;
    private String userYmd;
    private String userCi;

    @QueryProjection
    public UserDto(Integer userNo, String userId, String testInput, String userNm, Integer userScore, String userPhoneNo, String userAdd, String userYmd, String userCi) {
        this.userNo = userNo;
        this.userId = userId;
        this.testInput = testInput;
        this.userNm = userNm;
        this.userScore = userScore;
        this.userPhoneNo = userPhoneNo;
        this.userAdd = userAdd;
        this.userYmd = userYmd;
        this.userCi = userCi;
    }

}
