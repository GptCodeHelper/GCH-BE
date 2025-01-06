package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LAN_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lan extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "LAN_ID")
    private Integer lanId;

    @Column(name = "LAN_NM")
    private String lanNM;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "probId")
    private Prob prob;
}
