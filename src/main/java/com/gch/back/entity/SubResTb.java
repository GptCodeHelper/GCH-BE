package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SUB_RES_TB")
@Getter
@NoArgsConstructor
public class SubResTb extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RES_ID")
    private Integer resId;

    @Column(name = "SUB_MES")
    private String subMes;
}
