package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "USER_NO")
    private Integer userNo;


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
}
