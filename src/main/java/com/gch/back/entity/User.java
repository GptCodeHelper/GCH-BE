package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_TB")
@Getter
@NoArgsConstructor
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
}
