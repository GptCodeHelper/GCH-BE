package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUB_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sub extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROB_CON_ID")
    private Integer probConId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "probId")
    private Prob prob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSq")
    private User user;
}
