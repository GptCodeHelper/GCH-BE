package com.gch.back.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
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

    // 엔티티 생성 시 호출
    @PrePersist
    public void prePersist() {
        this.regTime = LocalDateTime.now();
        this.chgDt = this.regTime;
    }


    public void setChgDt() {

    }
    // 감사 정보 업데이트
    protected void updateAuditInfo(String chgId) {
        this.chgDt = LocalDateTime.now();
        this.chgId = chgId;
    }

    // 최초 등록자 설정 (한 번만 설정 가능)
    protected void setRegId(String regId) {
        if (this.regId == null) {
            this.regId = regId;
        }
    }
}
