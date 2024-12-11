package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LAN_TB")
@Getter
@NoArgsConstructor
public class Lan extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LAN_ID")
    private Integer lanId;

    @Column(name = "LAN_NM")
    private String lanNM;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "probId")
    private Prob prob;
}
