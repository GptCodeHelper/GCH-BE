package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TEST_TB")
@Getter
@NoArgsConstructor
public class Test extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEST_ID")
    private Integer testID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "probId")
    private Prob prob;

    @Column(name = "TEST_INPUT")
    private Integer testInput;

    @Column(name = "TEST_OUTPUT")
    private Integer testOutput;
}
