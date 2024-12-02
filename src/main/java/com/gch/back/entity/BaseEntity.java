package com.gch.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false, name = "REG_DT")
    private LocalDateTime regTime;

    @Column(updatable = false, name = "REG_ID")
    private String regId;

    @Column(name = "CHG_DT")
    private LocalDateTime chgDt;

    @Column(name = "CHG_ID")
    private String chgId;

}
