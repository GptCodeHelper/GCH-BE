package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SUB_RES_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubResTb extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "RES_ID")
    private Integer resId;

    @Column(name = "SUB_MES")
    private String subMes;
}
