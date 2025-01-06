package com.gch.back.dto;

import com.gch.back.entity.BaseEntity;
import com.gch.back.entity.Prob;
import com.gch.back.entity.User;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class SubDto extends BaseEntity {

    private Integer probConId;
    private Prob prob;
    private User user;

    @QueryProjection
    public SubDto(Integer probConId, Prob prob, User user) {
        this.probConId = probConId;
        this.prob = prob;
        this.user = user;
    }
}
