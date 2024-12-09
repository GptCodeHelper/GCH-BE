package com.gch.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "LOGIN_LOG_TB")
@Getter
@NoArgsConstructor
public class LoginLog extends BaseEntity{

    @Id
    @Column(name = "LOG_SEQ")
    private String logSeq;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "LOG_YMD")
    private LocalDateTime logYmd;

    @Column(name = "LOG_TYPE")
    private String logType;

    @Column(name = "AG_INF")
    private String agInf;
}
