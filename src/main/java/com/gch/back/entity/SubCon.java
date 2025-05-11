package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUB_CON_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCon extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_CON_ID")
    private Integer subConId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subId")
    private Sub sub;

    @Column(name = "SUB_CON_SORT")
    private String subConSort;

    @Column(name = "SUB_CON_CODE")
    private String subConCode;
}
