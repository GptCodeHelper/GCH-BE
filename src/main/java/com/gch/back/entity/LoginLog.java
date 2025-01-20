package com.gch.back.entity;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
=======
import jakarta.persistence.*;
>>>>>>> dev
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "LOGIN_LOG_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
