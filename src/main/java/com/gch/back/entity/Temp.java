package com.gch.back.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "TEMP_TABLE")
@Getter
public class Temp {
    @Id
    @GeneratedValue
    @Column(name = "TEMP_TABLE")
    private Long rcpStuffNo;
}
