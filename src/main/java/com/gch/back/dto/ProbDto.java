package com.gch.back.dto;

import com.gch.back.entity.BaseEntity;
import com.gch.back.entity.Lan;
import com.gch.back.entity.ProbCon;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProbDto extends BaseEntity {

    private Integer probId;
    private String probTit;
    private List<Lan> lans = new ArrayList<Lan>();
    private List<ProbCon> probCons = new ArrayList<ProbCon>();

    @QueryProjection
    public ProbDto(Integer probId, String probTit, List<Lan> lans, List<ProbCon> probCons) {
        this.probId = probId;
        this.probTit = probTit;
        this.lans = lans;
        this.probCons = probCons;
    }

}
