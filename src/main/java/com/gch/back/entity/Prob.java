package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PROB_TB")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prob extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROB_ID")
    private Integer probId;

    @Column(name = "PROB_TIT")
    private String probTit;

    @Builder.Default
    @OneToMany(mappedBy = "prob")
    private Set<Lan> lanProbs = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "prob")
    private Set<ProbCon> probCons = new HashSet<>();

}
