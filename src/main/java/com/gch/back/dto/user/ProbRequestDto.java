package com.gch.back.dto.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProbRequestDto {
    private String probTit;
    private List<LanRequest> lanProbs;
    private List<ProbConRequest> probCons;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LanRequest {
        private String lanNM;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProbConRequest {
        private String probConSort;
        private String probCont;
    }
}
