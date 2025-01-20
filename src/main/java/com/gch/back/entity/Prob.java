package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "prob")
    private List<Lan> lanProbs = new ArrayList<Lan>();

    @OneToMany(mappedBy = "prob")
    private List<ProbCon> probCons = new ArrayList<ProbCon>();

}
